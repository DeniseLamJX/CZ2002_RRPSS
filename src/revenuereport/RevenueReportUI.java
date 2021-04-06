package revenuereport;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.Month;
import invoice.Invoice;
import invoice.InvoiceController;

public class RevenueReportUI {

    public void mainApp() {
        int option;
        Scanner sc = new Scanner(System.in);

        do {
        	System.out.println("*******************");
			System.out.println("Revenue Report Page");
			System.out.println("*******************");
            System.out.println("1. Load Invoices");
            System.out.println("2. View Revenue by Period");
            System.out.println("3. Quit");

            option = sc.nextInt(); // Read user option
            sc.nextLine(); // Read newline character after hitting Enter key

            switch (option) {
                case 1:
                    loadInvoicesUI();
                    break;
                case 2:
                    revenueByPeriod();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please choose a valid option.");
                    break;
            }
        } while (option != 3);
    }

    private static void loadInvoicesUI() {
		ArrayList<Invoice> invoiceList = InvoiceController.getInvoiceList();
        if (invoiceList.size() == 0) {
            System.out.println("No invoices found.");
        } else {
        	System.out.println("*******************");
            System.out.println("Restaurant Invoices");
            System.out.println("*******************");
            for (Invoice invoice : invoiceList) {
                InvoiceController.viewInvoice(invoice.getInvoiceId());
            }
        }
    }

    private static void revenueByPeriod(){
    	ArrayList<Invoice> invoiceList = InvoiceController.getInvoiceList();
    	ArrayList<Invoice> periodicInvoice = new ArrayList<Invoice>();
    	float revenue = 0;
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter Period (1/2/3/4): ");
    	int option = sc.nextInt();

		switch(option) {
			case 1:
				for (Invoice invoice : invoiceList) {
					Month month = invoice.getTimeStamp().getMonth();
					if (month == Month.JANUARY || month == Month.FEBRUARY || month == Month.MARCH) revenue += invoice.getTotal();
                }
                System.out.println("Total Revenue for Q1: " + revenue);
				break;
			case 2:
				for (Invoice invoice : invoiceList) {
					Month month = invoice.getTimeStamp().getMonth();
					if (month == Month.APRIL || month == Month.MAY || month == Month.JUNE) revenue += invoice.getTotal();
                }
                System.out.println("Total Revenue for Q2: " + revenue);
				break;
			case 3:
				for (Invoice invoice : invoiceList) {
					Month month = invoice.getTimeStamp().getMonth();
					if (month == Month.JULY || month == Month.AUGUST || month == Month.SEPTEMBER) revenue += invoice.getTotal();
                }
                System.out.println("Total Revenue for Q3: " + revenue);
				break;
			case 4:
				for (Invoice invoice : invoiceList) {
					Month month = invoice.getTimeStamp().getMonth();
					if (month == Month.OCTOBER || month == Month.NOVEMBER || month == Month.DECEMBER) revenue += invoice.getTotal();
                }
                System.out.println("Total Revenue for Q4: " + revenue);
				break;
			default:
				System.out.println("Please choose a valid option.");
		}

    }

}
