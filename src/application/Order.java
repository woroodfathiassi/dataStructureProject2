package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	private String Cname;
	private String Cphone;
	private String brand;
	private String model;
	private int year;
	private String color;
	private String price;
	private Date orderDate;
	private String orderStatus;

	public Order(String Cname, String Cphone, String brand, String model, int year, String color, String price,
			Date orderDate, String orderStatus) {
		this.Cname = Cname;
		this.Cphone = Cphone;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}

	public Order(String brand, String model, int year, String color, String price, Date orderDate) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
		this.orderDate = orderDate;
	}

	public Order(String brand, String model, int year, String color, String price, Date orderDate, String orderStatus) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String Cname) {
		this.Cname = Cname;
	}

	public String getCphone() {
		return Cphone;
	}

	public void setCphone(String Cphone) {
		this.Cphone = Cphone;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	

	@Override
	public String toString() {
		String s = new SimpleDateFormat("dd/MM/yyyy").format(orderDate);
		return Cname + "," + Cphone + "," + brand + "," + model + "," + year + "," + color + "," + price + "," + s + ","
				+ orderStatus+"\n";
	}

}
