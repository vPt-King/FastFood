package FastFood.Entity;

public class Customer_Order {
	private Customer customer;
	private Order order;
	public Customer_Order(Customer customer, Order order) {
		this.customer = customer;
		this.order = order;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
