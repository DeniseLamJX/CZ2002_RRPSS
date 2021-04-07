package order;

import java.util.Scanner;
import table.TableController;

public class OrderUI {
	
	public void mainApp() {
		int option;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("***********");
			System.out.println("Orders Page");
			System.out.println("***********");
			System.out.println("1. Create Order");
			System.out.println("2. View Order");
			System.out.println("3. Add Items to Order");
			System.out.println("4. Remove Items from Order");
			System.out.println("5. Quit");
			
			option = sc.nextInt(); // Read user option
            sc.nextLine(); // Read newline character after hitting Enter key
            
			switch(option) {
				case 1:
					newOrderUI();
					break;
				case 2:
					viewOrderUI();
					break;
				case 3:
					addItemsUI();
					break;
				case 4:
					removeItemsUI();
					break;
				case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please choose a valid option.");
                    break;
			}
		} while (option != 5);
	}
	
	public static void newOrderUI() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter staff ID.");
		int staffID = sc.nextInt();
		System.out.println("Enter table ID: ");
		int tableID = sc.nextInt();
		OrderController.newOrder(tableID, staffID);
	}
	
	public static void viewOrderUI() {
		Scanner sc = new Scanner(System.in);
		TableController.showOccupiedTables();
		System.out.println("Enter table ID: ");
		int tableID = sc.nextInt();
		OrderController.printOrder(TableController.getTable(tableID).getOrder()); 
	}
	
	public static void addItemsUI() {
		Scanner sc = new Scanner(System.in);
		TableController.showOccupiedTables();
		System.out.println("Enter table ID: ");
		int tableID = sc.nextInt();
		OrderController.addItemsToOrder(TableController.getTable(tableID).getOrder());
	}
	
	public static void removeItemsUI() {
		Scanner sc = new Scanner(System.in);
		TableController.showOccupiedTables();
		System.out.println("Enter table ID: ");
		int tableID = sc.nextInt();
		OrderController.removeItemsFromOrder(TableController.getTable(tableID).getOrder());
	}
	
}