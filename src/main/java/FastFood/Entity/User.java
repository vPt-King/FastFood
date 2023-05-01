package FastFood.Entity;

public class User {
	private int id;
	private String name;
	private String created_at;
	private String gmail;
	private String password;
	private String phone;
	private String address;
	public User(int id, String name, String created_at, String gmail, String password, String phone, String address) {
		this.id = id;
		this.name = name;
		this.created_at = created_at;
		this.gmail = gmail;
		this.password = password;
		this.phone = phone;
		this.address = address;
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
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public User(int id, String gmail) {
		this.id = id;
		this.gmail = gmail;
	}
	public User() {
		super();
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
