package com.demo.cafeX;

import java.util.Scanner;
/**
 * Cafe X Ordering System with Menu options to choose from
 * @author Nara Chittaluri
 *
 */
public class CafeX {

	public static void main(String[] args) {
		Order order = new Order();
		System.out.println("Welcome to Cafe X");
		try (Scanner in = new Scanner(System.in)) {

			Menu.display();

			int selection = -1;
			do {
				try {
					String input = in.nextLine();
					selection = Integer.parseInt(input);
					if (selection < 0 || selection > 4) {
						System.out.println("Invalid selecton");
						Menu.display();
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Invalid Selection");
					Menu.display();
				}

				Item selectedItem = Menu.getItemByItemNumber(selection);
				if (selectedItem != null) {
					System.out.println("You selected: " + selectedItem.getItenName().getLabel());
					order.add(selectedItem);
					Menu.display();
				}
			} while (selection != 0);

			if (!order.isEmpty()) {
				order.displayBill();
			} else {
				System.out.println("You did not order any item");
			}
			System.out.println("Thank you for choosing Cafe X");
		}
	}
}
