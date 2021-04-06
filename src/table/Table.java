package table;

import order.Order;

public class Table {
	
	private int tableId;
	private int size;
	private TableStatus amStatus;
	private TableStatus pmStatus;
	private Order order;
	
	public Table(int tableId, int size) {
		this.tableId = tableId;
		this.size = size;
		amStatus = TableStatus.VACATED;
		pmStatus = TableStatus.VACATED;
	}
	
	public TableStatus getAmStatus() { return amStatus; }
	public void setAmStatus(TableStatus amStatus) { this.amStatus = amStatus; }
	
	public TableStatus getPmStatus() { return pmStatus; }
	public void setPmStatus(TableStatus pmStatus) { this.pmStatus = pmStatus; }
	
	public int getSize() { return size; }
	
	public int getTableId() { return tableId; }
	
	public Order getOrder() { return order; }
	public void setOrder(Order order) { this.order = order; }
	
	public boolean amAvailable() {
		if (amStatus == TableStatus.VACATED) return true;
		return false;
	}
	
	public boolean pmAvailable() {
		if (pmStatus == TableStatus.VACATED) return true;
		return false;
	}
	
	public boolean amOccupied() {
		if (amStatus == TableStatus.OCCUPIED) return true;
		return false;
	}
	
	public boolean pmOccupied() {
		if (pmStatus == TableStatus.OCCUPIED) return true;
		return false;
	}

}