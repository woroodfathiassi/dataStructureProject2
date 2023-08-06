package application;

public class Node1 {
	private Object element;
	private Node1 next;

	public Node1() {

	}

	public Node1(Object element) {
		this.element = element;
	}

	
	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public Node1 getNext() {
		return next;
	}

	public void setNext(Node1 next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return element +"" ;
	}

	
}
