package FastFood.Entity;

import java.util.List;

public class OrderData {
	private int customer_id;
	private List<Item> listItems;
	private double total;
	private double subTotal;
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public List<Item> getListItems() {
		return listItems;
	}
	public void setListItems(List<Item> listItems) {
		this.listItems = listItems;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public OrderData(int customer_id, List<Item> listItems, double total, double subTotal) {
		this.customer_id = customer_id;
		this.listItems = listItems;
		this.total = total;
		this.subTotal = subTotal;
	}
	
	
	
	
}
