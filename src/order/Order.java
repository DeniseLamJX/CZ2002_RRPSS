package order;

import java.util.ArrayList;
import java.time.LocalDateTime;
import menu.Menu;
import promo.Promo;

public class Order {
	private LocalDateTime timestamp;
	private int tableId;
	private int staffId;
	private ArrayList<Menu> alaCarte = new ArrayList<Menu>();
	private ArrayList<Promo> setPackages = new ArrayList<Promo>();
	
	public Order(int tableId, int staffId) {
		this.tableId = tableId;
		this.staffId = staffId;
		timestamp = LocalDateTime.now();
	}
	
	public LocalDateTime getTimeStamp() { return timestamp; }
	public int getTable() { return tableId; }
	public int getStaff() { return staffId; }
	
	public void addItem(Menu item) { alaCarte.add(item); }
	public void removeItem (int itemId) {
		for(int i = 0; i < alaCarte.size(); i++) {
			if (itemId == alaCarte.get(i).getId()) alaCarte.remove(i);
		}
	}
	
	public void addPromo (Promo item) { setPackages.add(item); }
	public void removePromo (int itemId) {
		for(int i = 0; i < setPackages.size(); i++) {
			if (itemId == setPackages.get(i).getId()) setPackages.remove(i);
		}
	}
	
	public ArrayList<Menu> getOrderItems() { return alaCarte; }
	public void printOrderItems() {
		for(int i = 0; i < alaCarte.size(); i++) {
			System.out.println(alaCarte.get(i).getId()+ ". " + alaCarte.get(i).getName());
		}
	}
	
	public ArrayList<Promo> getOrderPromos() { return setPackages; }
	public void printOrderPromos() {
		for(int i = 0; i < setPackages.size(); i++) {
			System.out.println(setPackages.get(i).getId()+ ". " + setPackages.get(i).getName());
		}
	}
	
	public void clearAllItems () {
		alaCarte.clear();
		setPackages.clear();
	}
}

