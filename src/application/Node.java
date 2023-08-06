package application;

public class Node {
	private Object element;
	private Node prev, next;

	
	public Node() {
	}

	public Node(Object elemen) {
		this.element = elemen;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
	public String print() {
		return element + "," + prev + "," + next;
	}


}
