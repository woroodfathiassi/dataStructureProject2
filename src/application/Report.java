package application;

public class Report {

	private String brand;
	private String price1;
	private String price2;
	private String heigh;
	private String lower;

	public Report(String brand, String price1, String price2, String heigh, String lower) {
		this.brand = brand;
		this.price1 = price1;
		this.price2 = price2;
		this.heigh = heigh;
		this.lower = lower;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public String getHeigh() {
		return heigh;
	}

	public void setHeigh(String heigh) {
		this.heigh = heigh;
	}

	public String getLower() {
		return lower;
	}

	public void setLower(String lower) {
		this.lower = lower;
	}

	@Override
	public String toString() {
		return brand + "," + price1 + "," + price2 + "," + heigh + "," + lower;
	}

}
