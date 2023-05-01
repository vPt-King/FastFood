package FastFood.Entity;

import java.util.Date;

public class Admin {
	private int id;
	private String gmail;
	private String name;
	private String password;
	private Date dateOfBirth;
	private String address;
	private String phone;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public Admin(int id, String gmail, String name, String password, Date dateOfBirth, String address, String phone) {
		this.id = id;
		this.gmail = gmail;
		this.name = name;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phone = phone;
	}
	public Admin(String gmail, String password) {
		this.gmail = gmail;
		this.password = password;
	}
	public Admin() {
	}
	
	
	
}
