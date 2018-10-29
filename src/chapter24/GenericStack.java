package chapter24;

import java.util.ArrayList;

public class GenericStack<E> {
	private ArrayList<E> list = new ArrayList<>();
	
	public int getSize() {
		return list.size();
	}
	
	public E peek() {
		return list.get(getSize() - 1);
	}
	
	public void push(E e) {
		list.add(e);
	}
	
	public E pop() {
		E e = list.get(getSize() - 1);
		list.remove(getSize() - 1);
		return e;
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	public String toString() {
		return "Stack: " + list.toString();
	}
}
