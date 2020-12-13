package com.demo.cafeX;

import java.math.BigDecimal;

/**
 *  Cafe X Menu Item
 * @author Nara Chittaluri
 *
 */
public class Item {

	private int itemNum;
	private ItemName itenName;
	private BigDecimal itemPrice;
	private ItemType itemType;

	public enum ItemName {
		COFFEE("coffee"), COLA("cola"), CHEESE_SANDWICH("Cheese Sandwich"), STEAK_SANDWICH("Steak Sandwich");

		private final String label;

		ItemName(String label) {
			this.label = label;
		}

		public String getLabel() {
			return this.label;
		}
	}

	public enum ItemType {
		DRINK, FOOD;
	}

	public Item(int itemNum, ItemName itemName, ItemType itemType, BigDecimal itemPrice) {
		this.itemNum = itemNum;
		this.itenName = itemName;
		this.itemPrice = itemPrice;
		this.itemType = itemType;
	}

	public int getItemNum() {
		return itemNum;
	}

	public ItemName getItenName() {
		return itenName;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public ItemType getItemType() {
		return itemType;
	}
}
