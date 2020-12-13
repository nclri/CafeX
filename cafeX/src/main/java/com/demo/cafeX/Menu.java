package com.demo.cafeX;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.demo.cafeX.Item.ItemName;
import com.demo.cafeX.Item.ItemType;

/**
 *  Cafe X Menu comprising of Items the restaurant offers.
 * @author Nara Chittaluri
 *
 */
public class Menu {
	private static List<Item> items;
	private static int itemNum = 1;
	private static final String LINE_BORDER = "*********************************************************************";

	static {
		items = new ArrayList<>();

		items.add(new Item(itemNum++, ItemName.COLA, ItemType.DRINK, new BigDecimal("0.50")));
		items.add(new Item(itemNum++, ItemName.COFFEE, ItemType.DRINK, new BigDecimal("1.00")));
		items.add(new Item(itemNum++, ItemName.CHEESE_SANDWICH, ItemType.FOOD, new BigDecimal("2.00")));
		items.add(new Item(itemNum++, ItemName.STEAK_SANDWICH, ItemType.FOOD, new BigDecimal("4.50")));
	}

	public static final List<Item> getItems() {
		return items;
	}

	public static Item getItemByItemNumber(final int itemNum) {
		for (Item item : items) {
			if (item.getItemNum() == itemNum)
				return item;
		}
		return null;
	}

	public static void display() {
		System.out.println(LINE_BORDER);
		System.out.println("Menu:");
		System.out.println("Enter");
		for (Item item : items) {
			System.out.println(item.getItemNum() + " for " + item.getItenName().getLabel());
		}
		System.out.println("0 to Quit");
		System.out.println(LINE_BORDER);
	}

}
