package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleFloatProperty;

public class SLL implements MYINTERFACE{
	private Node1 first, last;
	int count = 0;

	public SLL() {

	}

	public SLL(Node1 first, Node1 last) {
		this.first = first;
		this.last = last;
	}

	public void setFirst(Node1 first) {
		this.first = first;
	}

	public void setLast(Node1 last) {
		this.last = last;
	}

	public Node1 getFirst() {
		return first;
	}

	public Node1 getLast() {
		return last;
	}

	public void addFirst(Object element) {
		if (count == 0)
			first = last = new Node1(element);
		else {
			Node1 temp = new Node1(element);
			temp.setNext(first);
			first = temp;
		}
		count++;
	}

	public void addLast(Object element) {
		if (count == 0)
			first = last = new Node1(element);
		else {
			Node1 temp = new Node1(element);
			last.setNext(temp);
			last = temp;
		}
		count++;
	}

	public void add(Object element, int index) {
		if (count == 0)
			addFirst(element);
		else if (index == 0)
			addFirst(element);
		else if (index == count)
			addLast(element);
		else if (index < 0 || count <= index)
			addLast(element);
		else {
			Node1 temp = new Node1(element);
			Node1 current = first;
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			temp.setNext(current.getNext());
			current.setNext(temp);
		}
		count++;
	}

	public void addSorted(Object element) throws ParseException {
		if (isExisting((Cars) element))
			return;
		int index = 0;
		if (count == 0)
			addFirst(element);
		else if (((Cars) element).getYear() <= ((Cars) getFirst().getElement()).getYear())
			addFirst(element);
		else if (((Cars) element).getYear() >= ((Cars) getLast().getElement()).getYear())
			addLast(element);
		else {
			Node1 newnode = new Node1(element);
			Node1 current = first;
			while (((Cars) current.getElement()).getYear() <= ((Cars) element).getYear() && current.getNext() != null) {
				index++;
				current = current.getNext();
			}
			add(element, index);
		}
	}

	public boolean removeFirst() {
		if (count == 0)
			return false;
		else if (count == 1)
			first = last = null;
		else {
			Node1 temp = first;
			first = first.getNext();
			temp.setNext(null);
		}
		count--;
		return true;
	}

	public boolean removeLast() {
		if (count == 0)
			return false;
		else if (count == 1)
			first = last = null;
		else {
			Node1 current = first;
			while (current.getNext().getNext() != null)
				current = current.getNext();
			last = current;
			last.setNext(null);
			;
		}
		count--;
		return true;
	}

	public boolean remove(int index) {

		Node1 ptr = null, prev = null;
		if (index < 0 || index >= count)
			return false;
		else if (index == 0)
			return removeFirst();
		else if (index == count - 1)
			return removeLast();
		else {
			ptr = first;
			for (int i = 0; i < index; i++) {
				prev = ptr;
				ptr = ptr.getNext();

			}
			prev.setNext(ptr.getNext());
			ptr.setNext(null);

			count--;
			return true;
		}

	}

	public boolean remove(Object element) {
		Node1 prev = null, current = null;
		if (count != 0) {
			if (element.equals(first.getElement()))
				return removeFirst();
			else if (element.equals(last.getElement()))
				return removeLast();
			else {
				current = first;
				for (int i = 0; i < count; i++) {
					if (element.toString().equals(current.getElement().toString())) {
						System.out.println("remove index");
						return remove(i);
					}
					current = current.getNext();
				}
			}
		}
		System.out.println("i am in remove object");
		return false;
	}

	public void printList() {
		Node1 current = first;
		if (count == 0)
			return;
		while (current != null) {
			System.out.println(current.getElement().toString());
			current = current.getNext();
		}
	}


	public Object get(int index) {
		Node1 temp = first;
		if (index >= count || index < 0)
			return null;
		int i = 0;
		while (temp != null && i < index) {
			temp = temp.getNext();
			i++;
		}
		return temp.getElement();
	}

	public int indexOf(Object s) {
		Node1 current = first;
		int i = 0;
		if (count == 0)
			return -1;
		else if (count == 1)
			return 0;
		else {
			while (i < count && current != null) {
				if (current.getElement() == s) {
					break;
				}
				current = current.getNext();
				i++;

			}
			return i;

		}
	}

	public int getSize() {
		return count;
	}

	public boolean isExisting(Cars c) {
		String model = c.getModel();
		int year = c.getYear();
		String color = c.getColor();
		String price = c.getPrice();
		Node1 current = first;
		for (int i = 0; i < count; i++) {
			if (((Cars) current.getElement()).getModel().equals(model)
					&& ((Cars) current.getElement()).getYear() == year
					&& ((Cars) current.getElement()).getColor().equals(color)
					&& ((Cars) current.getElement()).getPrice().equals(price))
				return true;
			current = current.getNext();
		}

		return false;
	}

}
