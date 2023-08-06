package application;

import java.text.ParseException;

public class DLL implements MYINTERFACE{

	private Node first, last;
	int count = 0;

	public Node getfirst() {
		return first;
	}

	public Node getLast() {
		return last;
	}

	public void addLast(Object element) {
		Node newNode = new Node(element);

		if (count == 0) {
			first = last = newNode;
		} else {
			Node temp = new Node(element);
			last.setNext(temp);
			temp.setNext(first);
			first.setPrev(temp);
			temp.setPrev(last);
			last = temp;

		}
		count++;
	}

	public void addFirst(Object element) {
		if (count == 0)
			first = last = new Node(element);
		else {
			Node temp = new Node(element);
			first.setPrev(temp);
			temp.setNext(first);
			temp.setPrev(last);
			last.setNext(temp);
			first = temp;
		}

		count++;
	}

	public void add(Object element, int index) {
		Node newnode = new Node(element);
		Node current = first;
		if (count == 0)
			addFirst(element);
		else if (index == 0)
			addFirst(element);
		else if (index == count)
			addLast(element);
		else {
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			newnode.setNext(current.getNext());
			current.setNext(newnode);
			newnode.setPrev(current);
			newnode.getNext().setPrev(newnode);
			count++;
		}

	}

	public void addSorted(Object element) {
		if (isExisting(element.toString()))
			return;
		int index = 0;
		if (count == 0)
			addFirst(element);
		else if (element.toString().trim().compareToIgnoreCase(first.getElement().toString().trim()) < 0)
			addFirst(element);
		else if (element.toString().trim().compareToIgnoreCase(last.getElement().toString().trim()) > 0)
			addLast(element);
		else {
			Node newnode = new Node(element);
			Node current = first;
			while (current.getElement().toString().compareToIgnoreCase(element.toString()) < 0
					&& current.getNext() != null) {
				index++;
				current = current.getNext();
			}
			add(element, index);
		}

	}
	
	public void addSortedO(Object element) throws ParseException {
		int index = 0;
		if(count == 0)
			addFirst(element);
		else if (((Cars)element).getYear() <= ((Cars)getfirst().getElement()).getYear() )
			addFirst(element);
		else if (((Cars)element).getYear() >= ((Cars)getLast().getElement()).getYear())
			addLast(element);
		else {
			Node newnode = new Node(element);
			Node current = first;
			while(((Cars)current.getElement()).getYear() <= ((Cars)element).getYear() && current.getNext() != null ) {
				index++;
				current = current.getNext();
			}
			add(element, index);
		}
	}

	
	public boolean removeLast() {
		if (count == 0)
			return false;
		else if (count == 1) {
			first = last = null;
		} else {
			Node current = first;
			for (int i = 0; i < count - 1; i++)
				current = current.getNext();
			last.setPrev(current.getPrev());
			last = current;
			last.setNext(first);
		}
		count--;
		return true;
	}

	public boolean removeFirst() {
		if (count == 0)
			return false;
		else if (count == 1) {
			first = last = null;
		} else {
			Node current = first;
			first = first.getNext();
			first.setPrev(last);
			current = null;
		}
		count--;
		return true;
	}

	public boolean remove(int index) {
		Node prev = first;
		Node current = first;

		if (index == 0)
			return removeFirst();
		else if (index == count - 1)
			return removeLast();
		else if (index < 0 || index > count)
			return false;
		else {
			for (int i = 0; i < index; i++) {
				prev = current;
				current = current.getNext();

			}
			prev.setNext(current.getNext());
			current.getNext().setPrev(prev);
			count--;
			return true;
		}
	}

	public boolean remove(Object element) {
		if (count == 0)
			return false;
		if (element.equals(first.getElement()))
			return removeFirst();
		if (element.equals(last.getElement()))
			return removeLast();
		else {
			Node current = first.getNext();
			for (int i = 0; i < count; i++) {
				if (current.getElement().equals(element))
					return remove(i + 1);
				current = current.getNext();
			}
			return false;
		}
	}

	public String print() {
		String x = "";
		if (count == 0)
			return x;
		Node current = first;
		for (int i = 0; i < count; i++) {
			x += (current.getElement().toString()) + "\n";
			current = current.getNext();
		}
		return x;
	}

	public void printList() {
		if (count == 0)
			return;
		Node current = first;
		for (int i = 0; i < count; i++) {
			System.out.println(current.getElement().toString());
			current = current.getNext();
		}
	}

	public int getSize() {
		return count;
	}

	public boolean isExisting(String element) {
		Node current = first;
		for (int i = 0; i < count; i++) {
			if (element.toString().equalsIgnoreCase(current.getElement().toString()) && current != null)
				return true;
			current = current.getNext();
		}
		return false;
	}

	public int indexOf(Object s) {
		Node current = first;
		int i = 0;
		if (count == 0)
			return -1;
		else if (count == 1)
			return 0;
		else {
			while (i < count && current != null) {
				if (current.getElement().equals(s)) {
					break;
				}
				current = current.getNext();
				i++;

			}
			return i;

		}
	}

	public Node get(String str) {
		Node current = first;
		if (!isExisting(str))
			return null;
		else {
			for (int i = 0; i < count; i++) {
				if (current.getElement().toString().equalsIgnoreCase(str) && current != null)
					return current;
				current = current.getNext();
			}
		}

		return null;
	}

}
