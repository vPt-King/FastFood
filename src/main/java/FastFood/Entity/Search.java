package FastFood.Entity;

public class Search {
	private String option;
	private String value;
	public Search(String option, String value) {
		this.option = option;
		this.value = value;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
