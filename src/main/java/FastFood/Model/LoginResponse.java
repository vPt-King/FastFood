package FastFood.Model;

public class LoginResponse {
	private int status;
	private int id;
	private String message;
	public LoginResponse(int status, int id, String message) {
		this.status = status;
		this.id = id;
		this.message = message;
	}
	public LoginResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
}
