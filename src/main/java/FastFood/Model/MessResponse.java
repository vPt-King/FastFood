package FastFood.Model;

public class MessResponse {
	private int status;
	private String message;
	private int id;
	public MessResponse(int status, String message,int id) {
		this.status = status;
		this.message = message;
		this.id= id;
	}
	public MessResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}
}
