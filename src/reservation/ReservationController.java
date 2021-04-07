package reservation;

import java.util.ArrayList;
import java.time.LocalTime;
import table.Table;
import table.TableController;
import table.TableStatus;

public class ReservationController {
	
	private static ArrayList<Reservation> reservations = new ArrayList<Reservation>();

	public static void newReservation(int pax, int contactNumber) {
		if (TableController.tableAvailable() && pax <= 10) {
			int hour = LocalTime.now().getHour();
			int tableId;
			boolean found = false;
			
			if (hour < 12) {
				// matching with same-sized tables first
				for (Table table : TableController.amAvailableTables()) {
					if (pax == table.getSize()) {
						tableId = table.getTableId();
						table.setAmStatus(TableStatus.RESERVED);
						reservations.add(new Reservation(pax, contactNumber, tableId));
						System.out.println("Reservation successful. Table " + tableId + " is reserved.");
						found = true;
						break;
					}
				}
				// matching with smaller-sized tables
				if (!found) {
					for (Table table : TableController.amAvailableTables()) {
						if (pax < table.getSize()) {
							tableId = table.getTableId();
							table.setAmStatus(TableStatus.RESERVED);
							reservations.add(new Reservation(pax, contactNumber, tableId));
							System.out.println("Reservation successful. Table " + tableId + " is reserved.");
							System.out.println("Reservation successful.");
							found = true;
							break;
						}
					}
				}
			} else {
				// matching with same-sized tables first
				for (Table table : TableController.pmAvailableTables()) {
					if (pax == table.getSize()) {
						tableId = table.getTableId();
						table.setPmStatus(TableStatus.RESERVED);
						reservations.add(new Reservation(pax, contactNumber, tableId));
						System.out.println("Reservation successful. Table " + tableId + " is reserved.");
						System.out.println("Reservation successful.");
						found = true;
						break;
					}
				}
				// matching with smaller-sized tables
				if (!found) {
					for (Table table : TableController.pmAvailableTables()) {
						if (pax < table.getSize()) {
							tableId = table.getTableId();
							table.setPmStatus(TableStatus.RESERVED);
							reservations.add(new Reservation(pax, contactNumber, tableId));
							System.out.println("Reservation successful. Table " + tableId + " is reserved.");
							System.out.println("Reservation successful.");
							found = true;
							break;
						}
					}
				}
			}
			// all tables are smaller than requested size
			if (!found) System.out.println("No tables available to match size request.");
		} else {
			System.out.println("No tables available.");
		}
	}

	public static void checkReservation(int contactNumber) {
		boolean found = false;

		for (Reservation reservation : reservations) {
			if (reservation.getContactNumber() == contactNumber) {
				System.out.println("----- Reservation Details -----");
				System.out.println("Date: " + reservation.getDate());
				System.out.println("Time: " + reservation.getTime());
				System.out.println("Number of customers: " + reservation.getPax());
				System.out.println("Contact Number: " + reservation.getContactNumber());
				System.out.println("Table ID: " + reservation.getTableID());
				found = true;
				break;
			}
		}
		if (found == false) System.out.println("Invalid contact number.");
	}

	public static void removeReservation(int contactNumber) {
		boolean found = false;

		for (Reservation reservation : reservations) {
			if (reservation.getContactNumber() == contactNumber) {
				if (reservation.getTime().getHour() < 12) {
					TableController.getTable(reservation.getTableID()).setAmStatus(TableStatus.VACATED);
				} else {
					TableController.getTable(reservation.getTableID()).setPmStatus(TableStatus.VACATED);
				}
				reservations.remove(reservation);
				System.out.println("Reservation removed.");
				found = true;
				break;
			}
		}
		if (found == false) System.out.println("Invalid contact number.");
	}
	
}
