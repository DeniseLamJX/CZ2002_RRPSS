package order;

import java.util.Scanner;
import java.time.LocalTime;
import menu.Menu;
import menu.MenuController;
import promo.Promo;
import promo.PromoController;
import table.Table;
import table.TableController;
import table.TableStatus;

public class OrderController {
	
	public static void newOrder(int tableID, int staffID) {
		int hour = LocalTime.now().getHour();
		Order order = new Order(tableID, staffID);
		Table table = TableController.getTable(tableID);
		table.setOrder(order);
		if (hour < 12) {
			table.setAmStatus(TableStatus.OCCUPIED);
		} else {
			table.setPmStatus(TableStatus.OCCUPIED);
		}
		addItemsToOrder(order);
	}
	
	public static void addItemsToOrder(Order order) {
		int itemID;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("Please enter menu item ID. Enter -1 to quit.");
			itemID = sc.nextInt();
			for (Menu menu : MenuController.retrieveMenu()) {
				if (itemID == menu.getId()) order.addItem(menu);
			}
		} while (itemID != -1);
		
		do {
			System.out.println("Please enter promo item ID. Enter -1 to quit.");
			itemID = sc.nextInt();
			for (Promo promo : PromoController.retrievePromo()) {
				if (itemID == promo.getId()) order.addPromo(promo);
			}
		} while (itemID != -1);
			
		printOrder(order);
	}
	
	public static void removeItemsFromOrder(Order order) {
		int itemID;
		Scanner sc = new Scanner(System.in);
		printOrder(order);
		
		do {
			System.out.println("Please enter menu item ID. Enter -1 to quit.");
			itemID = sc.nextInt();
			order.removeItem(itemID);
		} while (itemID != -1);
		
		do {
			System.out.println("Please enter promo item ID. Enter -1 to quit.");
			itemID = sc.nextInt();
			order.removePromo(itemID);
		} while (itemID != -1);
		
		printOrder(order);
	}
	
	public static void printOrder(Order order) {
		System.out.println("Menu items ordered for table " + order.getTable() + "is now: ");
		order.printOrderItems();
		System.out.println("Promo items ordered for table " + order.getTable() + "is now: ");
		order.printOrderPromos();
	}

}