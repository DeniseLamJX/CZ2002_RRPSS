package invoice;

import table.TableController;
import java.util.Scanner;

public class InvoiceUI {

	public void mainApp() {
		int option;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("************");
			System.out.println("Invoice Page");
			System.out.println("************");
			System.out.println("1. Save Invoice");
			System.out.println("2. View Invoice");
			System.out.println("3. Quit");

			option = sc.nextInt();
			sc.nextLine();

			switch(option) {
				case 1:
					saveInvoiceUI();
					break;
				case 2:
					viewInvoiceUI();
					break;
				case 3:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Please choose a valid option.");
			}
		} while (option != 3);
	}
	
	public static void saveInvoiceUI() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Table Number:");
		int tableId = sc.nextInt();
		InvoiceController.saveInvoice(TableController.getTable(tableId));
	};
	
	public static void viewInvoiceUI() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Invoice ID:");
		int invoiceId = sc.nextInt();
		InvoiceController.viewInvoice(invoiceId);
	};

}
