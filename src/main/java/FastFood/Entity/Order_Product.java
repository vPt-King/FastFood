package FastFood.Entity;

public class Order_Product {
	private int id;
	private int product_id;
	private int order_id;
	private int quantity;
	private double sub_total;
	private String description;
	private String product_name;
	public Order_Product(int id, int product_id, int order_id, int quantity, double sub_total, String description,
			String product_name) {
		this.id = id;
		this.product_id = product_id;
		this.order_id = order_id;
		this.quantity = quantity;
		this.sub_total = sub_total;
		this.description = description;
		this.product_name = product_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSub_total() {
		return sub_total;
	}
	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
