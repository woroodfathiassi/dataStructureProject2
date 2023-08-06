package application;

import java.util.Date;

public class Cars {
	private String brand;
	private String model;
	private int year;
	private String color;
	private String price;

	public Cars(String brand, String model, int year, String color, String price) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
	}

	public Cars(String model, int year, String color, String price) {
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return brand + "," + model + "," + year + "," + color + "," + price+"\n";
	}

}
