package com.demo.cafeX;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.demo.cafeX.Item.ItemType;

/**
 * Cafe X Order entity , can add {@link Item} to Order
 * @author Nara Chittaluri
 *
 */
public class Order {
	private static final int SERVICE_CHARGE = 10;
	private static final String LINE_BORDER = "*********************************************************************";
	private static final String TOTAL_FORMAT = "%-47s %15.2f";
	private static final String NEW_LINE = "\n";

	Map<Item, Integer> itemsOrdered = new HashMap<>();

	/**
	 * Add Item to Order
	 * @param item
	 */
	public void add(final Item item) {
		if (itemsOrdered.get(item) == null) {
			itemsOrdered.put(item, 1);
		} else {
			int qty = itemsOrdered.get(item);
			itemsOrdered.put(item, ++qty);
		}
	}

	/**
	 * Checks if Customer has any {@link Item} added to Order
	 * @return true if Items in Order , false otherwise
	 */
	public boolean isEmpty() {
		return itemsOrdered.isEmpty();
	}

	/**
	 * subTotal before adding service Charge
	 * @return BigDecimal subTotal
	 */
	public BigDecimal calcSubTotal() {
		return itemsOrdered.entrySet().stream().map(e -> formatMoney(e.getKey().getItemPrice().multiply(new BigDecimal(e.getValue())))).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	/**
	 * Calculates service charge for the Order, if any
	 * <p> Service charge is added if Order has food 
	 * @param amount order subTotal
	 * @return service charge
	 */
	public BigDecimal calcServiceCharge(BigDecimal amount) {
		return formatMoney(amount.multiply(new BigDecimal(SERVICE_CHARGE)).divide(new BigDecimal(100)));
	}

	/**
	 * Verifies if Order has any food items
	 * @return true if Order has Food , false otherwise
	 */
	public boolean hasFood() {
		return itemsOrdered.keySet().stream().anyMatch(i -> i.getItemType() == ItemType.FOOD);
	}

	/**
	 * Calculates Order Total
	 * 	<p> Includes subTotal and service charge, if any
	 * @return total order amount
	 */
	public BigDecimal calcTotal() {
		BigDecimal total = calcSubTotal();
		if (hasFood()) {
			total = formatMoney(total.add(calcServiceCharge(total)));
		}
		return total;
	}

	public BigDecimal formatMoney(BigDecimal amount) {
		return amount.setScale(2, BigDecimal.ROUND_UP);
	}

	/**
	 * Display final sale receipt
	 */
	public void displayBill() {
		StringBuilder sb = new StringBuilder();
		sb.append(LINE_BORDER);
		sb.append(NEW_LINE);
		sb.append(String.format("%-15s %-15s %-15s %15s ", "Item", "Unit Price", "Quantity", "Total"));
		sb.append(NEW_LINE);
		sb.append(LINE_BORDER);
		sb.append(NEW_LINE);
		for (Map.Entry<Item, Integer> entry : itemsOrdered.entrySet()) {
			int qty = entry.getValue();
			Item item = entry.getKey();
			sb.append(String.format("%-15s %-15s %-15s %15.2f", item.getItenName().getLabel(), item.getItemPrice(), qty,
					item.getItemPrice().multiply(new BigDecimal(qty))));
			sb.append(NEW_LINE);
		}
		sb.append(LINE_BORDER);
		sb.append(NEW_LINE);
		if (hasFood()) {
			sb.append(String.format(TOTAL_FORMAT, "subTotal", calcSubTotal()));
			sb.append(NEW_LINE);
			sb.append(String.format(TOTAL_FORMAT, "service charge@" + SERVICE_CHARGE + "%",
					calcServiceCharge(calcSubTotal())));
			sb.append(NEW_LINE);
		}
		sb.append(String.format(TOTAL_FORMAT, "Total", calcTotal()));
		sb.append(NEW_LINE);
		sb.append(LINE_BORDER);
		System.out.println(sb.toString());
	}

}
