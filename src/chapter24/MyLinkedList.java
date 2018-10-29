package chapter24;

import java.util.Iterator;


public class MyLinkedList<E> extends MyAbstractList<E> {
	Node<E> head = null;
	Node<E> tail = null;
	public MyLinkedList() {
	}
	
	public MyLinkedList(E[] elements) {
		super(elements);
	}
	
	public void addFirst(E e) {
		Node<E> newNode= new Node<>(e);
		newNode.next = head;
		head = newNode;
		size++;
		
		if(tail == null)
			tail = head;
	}
	
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e);
		
		if(tail == null)
			head = tail = newNode;
		else {
			tail.next = newNode;
			tail = tail.next;
		}
		size++;
	}
	
	@Override
	public void add(int index, E e) {
		if(index == 0) addFirst(e);
		else if (index >= size) addLast(e);
		else {
			Node<E> current = head;
			for(int i = 0; i < index - 1; i++)
				current = current.next;
			Node<E> temp = current.next;
			current.next = new Node<E>(e);
			current.next.next = temp;
			size++;
		}
	}

	public E removeFirst() {
		if(size == 0) return null;
		else {
			Node<E> temp = head;
			head = head.next;
			size--;
			if(head == null)
				tail = null;
			return temp.element;
		}
	}
	
	public E removeLast() {
		if(size == 0) return null;
		else if(size == 1) {
			Node<E> temp = head;
			head = tail = null;
			size = 0;
			return temp.element;
		} else {
			Node<E> current = head;
			
			for(int i = 0; i < size - 2; i++)
				current = current.next;
			
			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}
	
	@Override
	public E remove(int index) {
		if(index < 0 || index >= size) return null;
		else if(index == 0) return removeFirst();
		else if(index == size - 1) return removeLast();
		else {
			Node<E> previous = head;
			
			for(int i = 1; i < index; i++) 
				previous = previous.next;
			
			Node<E> current = previous.next;
			previous.next = current.next;
			size--;
			return current.element;
		}
	}
	
	public E getFirst() {
		if(size == 0)
			return null;
		else
			return head.element;
	}
	
	public E getLast() {
		if(size == 0)
			return null;
		else 
			return tail.element;
	}
	
	@Override
	public void clear() {
		size = 0;
		head = tail = null;
	}

	@Override
	public String toString() {
		if(size == 0) return "[]";
		StringBuilder result = new StringBuilder("[");
		
		Node<E> current = head;
		for(int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if(current != null) {
				result.append(", ");
			} else {
				result.append("]");
			}
		}
		
		return result.toString();
	}

	@Override
	public boolean contains(E e) {
		Node<E> temp = head;
		while(temp != null) {
			if(temp.element.equals(e))
				return true;
			temp = temp.next;
		}
		return false;
	}

	@Override
	public E get(int index) {
		if(index < 0 || index >= size)
			return null;
		else {
			Node<E> current = head;
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			return current.element;
		}
	}

	@Override
	public int indexOf(E e) {
		int index = 0;
		Node<E> temp = head;
		while(temp != null) {
			if(temp.element.equals(e)){
				return index;
			} else {
				temp = temp.next;
				index++;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E e) {
		Node<E> temp = head;
		int index = 0;
		int ans = 0;
		boolean isFind = false;
		while(temp != null) {
			if(!temp.element.equals(e)) {
				temp = temp.next;
				index++;
			} else {
				isFind = true;
				ans = index; 
				index++;
				temp = temp.next;
			}
		}
		if(isFind)
			return ans;
		return -1;
	}

	@Override
	public Object set(int index, E e) {
		Node<E> ans = null;
		if(index < 0 || index >= size || size == 0)
			return null;
		if(index == 0) {
			if(size == 1) {
				Node<E> node = new Node<>(e);
				ans = head;
				head = tail = node;
				return ans.element;
			} else {
				ans = head;
				Node<E> node = new Node<>(e);
				node.next = head.next;
				head = node;
				return ans.element;
			}
		}
		else {
			Node<E> temp = head;
			for(int i = 0; i < index - 1; i++) {
				temp = temp.next;
			}
			ans = temp.next;
			Node<E> nodeNext = temp.next.next;
			Node<E> node = new Node<>(e);
			temp.next = node;
			node.next = nodeNext;
			if(nodeNext == null) tail = node;
		}
		return ans.element;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator
		implements java.util.Iterator<E>{
		private Node<E> current = head;
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			E e = current.element;
			current = current.next;
			return e;
		}
		
		@Override
		public void remove() {
			MyLinkedList.this.remove(indexOf(current.element));
		}
	}
	private static class Node<E>{
		E element;
		Node<E> next;
		
		public Node(E element) {
			this.element = element;
			this.next = null;
		}
	}
}
