package reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

	private LocalDate date;
	private LocalTime time;
	private int pax;
	private int contactNumber;
	private int tableId;

	public Reservation(int pax, int contactNumber, int tableId) {
		this.date = LocalDate.now();
		this.time = LocalTime.now();
		this.pax = pax;
		this.contactNumber = contactNumber;
		this.tableId = tableId;
	}

	public LocalDate getDate() { return date; }
	public LocalTime getTime() { return time; }
	public int getPax() { return pax; }
	public int getContactNumber() { return contactNumber; }
	public int getTableID() { return tableId; }

}
