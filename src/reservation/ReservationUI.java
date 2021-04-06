package reservation;

import java.util.Scanner;
import table.TableController;

public class ReservationUI {

	public void mainApp() {
		int option;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("****************");
			System.out.println("Reservation Page");
			System.out.println("****************");
			System.out.println("1. Create a reservation");
			System.out.println("2. Check a reservation");
			System.out.println("3. Remove a reservation");
			System.out.println("4. Check table status");
			System.out.println("5. Exit Reservation UI");

			option = sc.nextInt(); // Read user option
            sc.nextLine(); // Read newline character after hitting Enter key

			switch(option) {
				case 1:
					createReservationUI();
					break;
				case 2:
					checkReservationUI();
					break;
				case 3:
					removeReservationUI();
					break;
				case 4:
					checkTableStatusUI();
					break;
				case 5:
					System.out.println("Exiting...");
					break;
				default:
                    System.out.println("Please choose a valid option.");
			}
		} while (option != 5);
	}
	
	public static void createReservationUI() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of customers: ");
		int pax = sc.nextInt();
		System.out.println("Enter customer's contact number: ");
		int contactNumber = sc.nextInt();
		ReservationController.newReservation(pax, contactNumber);
	};
	
	public static void checkReservationUI() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer's contact number: ");
		int contactNumber = sc.nextInt();
		ReservationController.checkReservation(contactNumber);
	};
	
	public static void removeReservationUI() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer's contact number: ");
		int contactNumber = sc.nextInt();
		ReservationController.removeReservation(contactNumber);
	};
	
	public static void checkTableStatusUI() {
		TableController.showTableStatus();
	};
}
