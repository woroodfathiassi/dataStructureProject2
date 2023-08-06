package application;

public class Brand {
	private String brand;
	private SLL list;

	public Brand(String brand) {
		this.brand = brand;
		list = new SLL();
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public SLL getList() {
		return list;
	}

	public void setList(SLL list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return brand;
	}

}
