package FastFood.Entity;

public class Shop {
	private int id;
	private String name;
	private String address;
	private String phone;
	private String gmail;
	private int tax;
	private double shipping;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getGmail() {
		return gmail;
	}



	public void setGmail(String gmail) {
		this.gmail = gmail;
	}



	public int getTax() {
		return tax;
	}



	public void setTax(int tax) {
		this.tax = tax;
	}



	public double getShipping() {
		return shipping;
	}



	public void setShipping(double shipping) {
		this.shipping = shipping;
	}



	public Shop(int id, String name, String address, String phone, String gmail, int tax, double shipping) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.gmail = gmail;
		this.tax = tax;
		this.shipping = shipping;
	}
	
}
