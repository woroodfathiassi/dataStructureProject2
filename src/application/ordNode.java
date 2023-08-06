package application;

public class ordNode {
	private Order element;
	private ordNode next;

	
	public ordNode(Order element) {
		this.element = element;
	}

	
	public Order getElement() {
		return element;
	}

	public void setElement(Order element) {
		this.element = element;
	}

	public ordNode getNext() {
		return next;
	}

	public void setNext(ordNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return element +"" ;
	}

	
}
