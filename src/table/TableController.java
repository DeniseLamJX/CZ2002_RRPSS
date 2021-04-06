package table;

import java.util.ArrayList;

public class TableController {
	
	private static final int NUMOFTABLES = 10;
	private static Table[] tableList = new Table[NUMOFTABLES];

	// Table IDs: 0 to 9 - Each sizes has 2 tables
	public static void createTables() {
		for (int i = 0; i < 2; i++) tableList[i] = new Table(i, 2);
		for (int i = 2; i < 4; i++) tableList[i] = new Table(i, 4);
		for (int i = 4; i < 6; i++) tableList[i] = new Table(i, 6);
		for (int i = 6; i < 8; i++) tableList[i] = new Table(i, 8);
		for (int i = 8; i < 10; i++) tableList[i] = new Table(i, 10);
	}
	
	// returns all tables
	public static Table[] getTableList() { return tableList; }

	// returns specific table
	public static Table getTable(int tableId) {
		for (int i = 0; i < NUMOFTABLES; i++) {
			if (tableList[i].getTableId() == tableId) return tableList[i];
		}
		System.out.println("Table not found.");
		return null;
	}
	
	// check if tables are available
	public static boolean tableAvailable() {
		for (Table table : tableList) {
			if (table.amAvailable() || table.pmAvailable()) return true;
		}
		return false;
	}

	// prints available tables
	public static void showAvailableTables() {
		System.out.println("Available Tables:");
		for (int i = 0; i < tableList.length; i++) {
			if (tableList[i].amAvailable() || tableList[i].pmAvailable()) {
				System.out.println("Table Id: " + tableList[i].getTableId());
				System.out.println("Size: " + tableList[i].getSize());
				System.out.println("AM Status: " + tableList[i].getAmStatus());
				System.out.println("PM Status: " + tableList[i].getPmStatus());
				System.out.println("");
			}
		}
	}
	
	// returns AM available tables
	public static ArrayList<Table> amAvailableTables() {
		ArrayList<Table> availableTables = new ArrayList<Table>();
		for (int i = 0; i < tableList.length; i++) {
			if (tableList[i].amAvailable()) availableTables.add(tableList[i]);
		}
		return availableTables;
	}
	
	// returns PM available tables
	public static ArrayList<Table> pmAvailableTables() {
		ArrayList<Table> availableTables = new ArrayList<Table>();
		for (int i = 0; i < tableList.length; i++) {
			if (tableList[i].pmAvailable()) availableTables.add(tableList[i]);
		}
		return availableTables;
	}

	// prints occupied tables (i.e. tables with orders)
	public static void showOccupiedTables() {
		boolean occupied = false;
		
		System.out.println("Tables with Orders:");
		for (int i = 0; i < tableList.length; i++) {
			if (tableList[i].amOccupied() || tableList[i].pmOccupied()) {
				System.out.println("Table Id: " + tableList[i].getTableId());
				occupied = true;
			}
		}
		if (occupied == false) System.out.println("No orders yet.");
	}
	
	// prints table status
	public static void showTableStatus() {
		System.out.println("Table Status:");
		for (int i = 0; i < tableList.length; i++) {
			System.out.println("Table Id: " + tableList[i].getTableId());
			System.out.println("Size: " + tableList[i].getSize());
			System.out.println("AM Status: " + tableList[i].getAmStatus());
			System.out.println("PM Status: " + tableList[i].getPmStatus());
			System.out.println("");
		}
	}
	
}