package FastFood.Entity;

public class Contact_details {
	private int id;
	private String address;
	private String gmap;
	private String pn1;
	private String pn2;
	private String email;
	private String fb;
	private String insta;
	private String tw;
	private String iframe;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGmap() {
		return gmap;
	}
	public void setGmap(String gmap) {
		this.gmap = gmap;
	}
	public String getPn1() {
		return pn1;
	}
	public void setPn1(String pn1) {
		this.pn1 = pn1;
	}
	public String getPn2() {
		return pn2;
	}
	public void setPn2(String pn2) {
		this.pn2 = pn2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFb() {
		return fb;
	}
	public void setFb(String fb) {
		this.fb = fb;
	}
	public String getInsta() {
		return insta;
	}
	public void setInsta(String insta) {
		this.insta = insta;
	}
	public String getTw() {
		return tw;
	}
	public void setTw(String tw) {
		this.tw = tw;
	}
	public String getIframe() {
		return iframe;
	}
	public void setIframe(String iframe) {
		this.iframe = iframe;
	}
	public Contact_details(int id, String address, String gmap, String pn1, String pn2, String email, String fb,
			String insta, String tw, String iframe) {
		this.id = id;
		this.address = address;
		this.gmap = gmap;
		this.pn1 = pn1;
		this.pn2 = pn2;
		this.email = email;
		this.fb = fb;
		this.insta = insta;
		this.tw = tw;
		this.iframe = iframe;
	}
	
}
