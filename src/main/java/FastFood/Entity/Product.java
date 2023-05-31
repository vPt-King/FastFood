package FastFood.Entity;

public class Product {
	private int id;
	private String name;
	private String description;
	private int quantity;
	private double price;
	private int category_id;
	private String image;
	public Product(int id, String name, String description, int quantity, double price, int category_id, String image) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.category_id = category_id;
		this.image = image;
	}
	public Product() {
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Product(String name, String description, int quantity, double price, int category_id, String image) {
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.category_id = category_id;
		this.image = image;
	}
	public Product(int id) {
		this.id = id;
	}
	public Product(int id, String name, String description, int quantity, double price, int category_id) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.category_id = category_id;
	}
	
	
}
