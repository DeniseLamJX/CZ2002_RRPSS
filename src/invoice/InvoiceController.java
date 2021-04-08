package invoice;

import java.util.ArrayList;
import java.util.List;

import database.SerializeDB;
import database.exceptions.FailReadException;
import database.exceptions.FailWriteException;
import base.BaseController;
import menu.Menu;
import promo.Promo;
import table.Table;
import order.Order;

public class InvoiceController extends BaseController{
    private static ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
    
    private InvoiceController() {super();};
    
    public static void saveInvoice(Table table){
    	//throw some exceptions
    	Order order = table.getOrder();
    	Invoice invoice = new Invoice(order);
    	invoice.setInvoiceId(invoiceList.size());
    	
    	float subtotal = invoice.getSubtotal();
    	ArrayList<Menu> menuItems = invoice.getMenuItems();
    	ArrayList<Promo> promoItems = invoice.getPromoItems();
    	
    	for (Menu item : order.getOrderItems()) {
    		menuItems.add(item);
    		subtotal += item.getPrice();
    	}
    	
    	for (Promo item : order.getOrderPromos()) {
    		promoItems.add(item);
    		subtotal += item.getPrice();
    	}
    	
    	invoice.setMenuItems(menuItems);
    	invoice.setPromoItems(promoItems);
    	invoice.setSubtotal(subtotal);

	int hour = order.getTimeStamp().getHour();
    	if (hour < 12) {
    		table.setAmStatus(TableStatus.VACATED);
    	} else {
    		table.setPmStatus(TableStatus.VACATED);
    	}
    	//order.deleteOrder(table.getTableId());
    	invoiceList.add(invoice);
    	saveInvoiceListToFile(invoiceList);

        System.out.println("InvoiceID " + invoice.getInvoiceId() + " saved successfully.");
    }


    public static void viewInvoice (int invoiceId) {
    	ArrayList<Invoice> invoiceList = null;
    	try {
    		invoiceList = retrieveInvoiceList();
    	}
    	catch (FailReadException e) {
    		System.out.println("e.getMessage()");
    	}
    	Invoice invoice = invoiceList.get(invoiceId - 1);
    	int tableId = invoice.getOrder().getTable();

        System.out.println("================Invoice===============");
        System.out.println("InvoiceID : " + invoiceId);
        System.out.println("Table Number: " + tableId);
        System.out.println("Date: " + invoice.getTimeStamp());
        System.out.println("Subtotal: " + invoice.getSubtotal());
        System.out.println("Service Charge: " + invoice.getServiceCharge());
        System.out.println("GST: " + invoice.getGst());
        System.out.println("Total Due: " + invoice.getTotal());
        System.out.println("");
    }

    public static ArrayList<Invoice> getInvoiceList() { return invoiceList; }
    
    public static ArrayList<Invoice> retrieveInvoiceList() throws FailReadException { //
    	List list = SerializeDB.readSerializedObject("invoice.dat");
        return list == null ? new ArrayList<>() : (ArrayList) list; 
    }
    
    //save invoice list to file array list    
     private static void saveInvoiceListToFile(ArrayList<Invoice> invoiceList) throws FailWriteException{
    	 SerializeDB.writeSerializeObject("invoice.dat", invoiceList);
 }
     //private static void saveInvoiceListToFile1(Object invoice) throws FailWriteException{
	//SerializeDB.writeSerializeObject("invoice.dat", invoice);
  //   }
}
