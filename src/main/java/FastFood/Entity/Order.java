package FastFood.Entity;

public class Order {
	private int id;
	private int customer_id;
	private String customer_name;
	private int quantity;
	private String started_date;
	private String due_date;
	private double sub_total;
	private double total;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStarted_date() {
		return started_date;
	}
	public void setStarted_date(String started_date) {
		this.started_date = started_date;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public double getSub_total() {
		return sub_total;
	}
	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Order(int id, int customer_id, String customer_name, int quantity, String started_date, String due_date, double sub_total,
			double total) {
		this.id = id;
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.quantity = quantity;
		this.started_date = started_date;
		this.due_date = due_date;
		this.sub_total = sub_total;
		this.total = total;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Order(int id, int customer_id, String customer_name, int quantity, String started_date, String due_date,
			double sub_total, double total, int status) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.quantity = quantity;
		this.started_date = started_date;
		this.due_date = due_date;
		this.sub_total = sub_total;
		this.total = total;
		this.status = status;
	}
	public Order(int customer_id) {
		this.customer_id = customer_id;
	}
	public Order() {
	}
	
	
}
