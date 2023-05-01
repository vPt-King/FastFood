package FastFood.Entity;

public class User_Customer {
	private Customer customer;
	private User user;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public User getUser() {
		return user;
	}
	public User_Customer(Customer customer, User user) {
		this.customer = customer;
		this.user = user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
