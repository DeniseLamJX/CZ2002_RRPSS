package invoice;

import java.util.ArrayList;
import java.time.LocalDateTime;
import order.Order;
import menu.Menu;
import promo.Promo;

public class Invoice {
	private static final float SERVICE_CHARGE = (float) 0.1;
	private static final float GST = (float) 0.07;
	
	private int invoiceId;
	private float subtotal;
	private float serviceCharge = subtotal * SERVICE_CHARGE;
    private float gst = subtotal * GST;
    private float total = subtotal + serviceCharge + GST;
	private Order order;
	private LocalDateTime timestamp;
	private ArrayList<Menu> menuItems = new ArrayList<Menu>();
	private ArrayList<Promo> promoItems = new ArrayList<Promo>();
	
	public Invoice(Order order) {
		timestamp = LocalDateTime.now();
		this.order = order;
	}

	public float getSubtotal() { return subtotal; }
	public void setSubtotal(float subtotal) { this.subtotal = subtotal; }
	
	public int getInvoiceId() { return invoiceId; }
	public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }
	
	public ArrayList<Menu> getMenuItems() { return menuItems; }
	public void setMenuItems(ArrayList<Menu> menuItems) { this.menuItems = menuItems; }
	
	public ArrayList<Promo> getPromoItems() { return promoItems; }
	public void setPromoItems(ArrayList<Promo> promoItems) { this.promoItems = promoItems; }
	
	public float getServiceCharge() { return serviceCharge; }
    public float getGst() { return gst; }
    public float getTotal() { return total; }
	public Order getOrder() { return order; }
	public LocalDateTime getTimeStamp() { return timestamp; }
	
	
}
