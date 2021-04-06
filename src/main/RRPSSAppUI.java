package main;

import base.BaseCRUD;
import invoice.InvoiceUI;
import menu.MenuUI;
import promo.PromoUI;
import reservation.ReservationUI;
import order.OrderUI;
import revenuereport.RevenueReportUI;
import table.TableController;

import java.util.Scanner;

public class RRPSSAppUI {

    public static void main(String []args) {
    	createTables();
    	startApp();
    }

    private static void startApp() {
        int option;
        BaseCRUD crudUI;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("What do you want work with today?");
            System.out.println("1. Menu");
            System.out.println("2. Promotion");
            System.out.println("3. Reservation");
            System.out.println("4. Order");
            System.out.println("5. Invoice");
            System.out.println("6. Revenue Report");
            System.out.println("7. Quit");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    crudUI = new MenuUI();
                    crudUI.startUI();
                    break;
                case 2:
                    crudUI = new PromoUI();
                    crudUI.startUI();
                    break;
                case 3:
                	ReservationUI reservationUI = new ReservationUI();
                	reservationUI.mainApp();
                    break;
                case 4:
                	OrderUI orderUI = new OrderUI();
                    orderUI.mainApp();
                    break;
                case 5:
                	InvoiceUI invoiceUI = new InvoiceUI();
                	invoiceUI.mainApp();
                    break;
                case 6:
                	RevenueReportUI revenuereportUI = new RevenueReportUI();
                	revenuereportUI.mainApp();
                    break;
                case 7:
                    System.out.println("Terminating... Goodbye!");
                    break;
                default:
                    System.out.println("Please choose a valid option.");
                    break;
            }
        } while (option != 7);
    }
    
    private static void createTables() {
    	TableController tableController = new TableController();
    	tableController.createTables();
    }
}
