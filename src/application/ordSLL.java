package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleFloatProperty;

public class ordSLL implements MYINTERFACE{
	private ordNode first, last;
	int count = 0;

	public ordSLL() {

	}

	public ordSLL(ordNode first, ordNode last) {
		this.first = first;
		this.last = last;
	}

	public void setFirst(ordNode first) {
		this.first = first;
	}

	public void setLast(ordNode last) {
		this.last = last;
	}

	public ordNode getFirst() {
		return first;
	}

	public ordNode getLast() {
		return last;
	}

	public void addFirst(Order element) {
		if (count == 0)
			first = last = new ordNode(element);
		else {
			ordNode temp = new ordNode(element);
			temp.setNext(first);
			first = temp;
		}
		count++;
	}

	public void addLast(Order element) {
		if (count == 0)
			first = last = new ordNode(element);
		else {
			ordNode temp = new ordNode(element);
			last.setNext(temp);
			last = temp;
		}
		count++;
	}

	public void add(Order element, int index) {
		if (count == 0)
			addFirst(element);
		else if (index == 0)
			addFirst(element);
		else if (index == count)
			addLast(element);
		else if (index < 0 || count <= index)
			addLast(element);
		else {
			ordNode temp = new ordNode(element);
			ordNode current = first;
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			temp.setNext(current.getNext());
			current.setNext(temp);
		}
		count++;
	}

	public void addSortedO(Order element) throws ParseException {

		int index = 0;
		if (count == 0)
			addFirst(element);
		else if (((Order) element).getYear() <= ((Order) getFirst().getElement()).getYear())
			addFirst(element);
		else if (((Order) element).getYear() >= ((Order) getLast().getElement()).getYear())
			addLast(element);
		else {
			ordNode newnode = new ordNode(element);
			ordNode current = first;
			while (((Order) current.getElement()).getYear() <= ((Order) element).getYear()
					&& current.getNext() != null) {
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
			ordNode temp = first;
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
			ordNode current = first;

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
		ordNode ptr = null, prev = null;
		if (index < 0 || index >= count)
			return false;
		else if (index == 0)
			return removeFirst();
		else if (index == count - 1)
			return removeLast();
		else {
			ptr = first;
			for (int i = 1; i <= index; i++) {
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
		ordNode prev = null, current = null;
		if (count != 0) {

			if (count == 1)
				removeFirst();
			if (element.equals(first.getElement()))
				return removeFirst();
			else if (element.equals(last.getElement()))
				return removeLast();
			else {
				current = first;
				for (int i = 0; i < count; i++) {
					if (element.equals(current.getElement()))
						return remove(i);
					current = current.getNext();
				}
			}
		}
		System.out.println("i am in remove object");
		return false;
	}

	public void printList() {
		ordNode current = first;
		if (count == 0)
			return;
		while (current != null) {
			System.out.println(current.getElement().toString());
			current = current.getNext();
		}
	}

	public Object get(int index) {
		ordNode temp = first;
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
		ordNode current = first;
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

}
