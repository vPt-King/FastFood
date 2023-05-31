package FastFood.Entity;

public class Settings {
	private int id;
	private String site_title;
	private String site_about;
	private int shutdown;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSite_title() {
		return site_title;
	}
	public void setSite_title(String site_title) {
		this.site_title = site_title;
	}
	public String getSite_about() {
		return site_about;
	}
	public void setSite_about(String site_about) {
		this.site_about = site_about;
	}
	public int getShutdown() {
		return shutdown;
	}
	public void setShutdown(int shutdown) {
		this.shutdown = shutdown;
	}
	public Settings(int id, String site_title, String site_about, int shutdown) {
		this.id = id;
		this.site_title = site_title;
		this.site_about = site_about;
		this.shutdown = shutdown;
	}
}
