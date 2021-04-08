package order.exception;

public class OrderNotFound extends Exception {
    public OrderNotFound() {
        super("Order not found for this table. Please create a new order for this table.");
    }
}
