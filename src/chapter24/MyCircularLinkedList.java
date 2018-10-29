package chapter24;

import java.util.Iterator;

public class MyCircularLinkedList<E> extends MyAbstractList<E> {
	Node<E> head = null;
	
	public MyCircularLinkedList() {
	}
	
	public MyCircularLinkedList(E[] objects){
		for (int i = 0; i < objects.length; i++) {
			add(objects[i]);
		}
	}
	
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e);
		if(size == 1) {
			newNode.next = head;
			head.next = newNode;
			head = newNode;
		} else if(size == 0){
			head = newNode;
			head.next = head;
		} else {
			Node<E> temp = head;
			while(temp.next != head)
				temp = temp.next;
			newNode.next = head;
			temp.next = newNode;
			head = newNode;
		}
		size++;
	}
	
	public void addLast(E e) {
		if(size == 0) addFirst(e);
		else {
			Node<E> newNode = new Node<E>(e);
			Node<E> temp = head;
			size++;
			while(temp.next != head) 
				temp = temp.next;
			temp.next = newNode;
			newNode.next = head;
//			while(head.next != temp)
//				head = head.next;
//			head.next = newNode;
//			newNode.next = temp;
//			head = temp;
		}
	}
	
	@Override
	public void add(int index, E e) {
		if(index == 0) addFirst(e);
		else if(index >= size) addLast(e);
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

	@Override
	public void clear() {
		size = 0;
		head = null;
	}

	public E removeFirst() {
		if(size == 0)
			return null;
		else if(size == 1) {
			Node<E> temp = head;
			head = null;
			size--;
			return temp.element;
		}
		else {
			Node<E> temp = head;
			Node<E> current = head;
			while(current.next != head)
				current = current.next;
			current.next = head.next;
			head = head.next;
			size--;
			return temp.element;
		}
	}
	
	public E removeLast() {
		if(size == 0) return null;
		else if(size == 1){
			Node<E> temp = head;
			head = null;
			size--;
			return temp.element;
		} else {
			Node<E> current = head;
			
			while(current.next.next != head)
				current = current.next;
			Node<E> temp = current.next;
			current.next = head;
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
			
			for(int i = 0; i < index - 1; i++) 
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
		else if(size == 1)
			return head.element;
		else {
			Node<E> temp = head;
			while(temp.next != head)
				temp = temp.next;
			return temp.element;
		}
	}

	@Override
	public String toString() {
		if(size == 0) return "[]";
		StringBuilder result = new StringBuilder("[");
		
		Node<E> current = head;
		for(int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if(current != head) {
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
		for(int i = 0; i < size; i++) {
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
		for(int i = 0; i < size; i++) {
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
		for(int i = 0; i < size; i++) {
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
		if(index < 0 || index >= size)
			return null;
		else if(index == 0) {
			if(size == 1) {
				ans = head;
				Node<E> newNode = new Node<>(e);
				head = newNode;
				head.next = head;
				return ans.element;
			}else {
				ans = head;
				Node<E> newNode = new Node<>(e);
				Node<E> temp = head;
				while(temp.next != head)
					temp = temp.next;
				newNode.next = head.next;
				head = newNode;
				temp.next = head;
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
		}
		return ans.element;
	}

	@Override
	public Iterator<E> iterator() {
		return new CircularLinkedListIterator();
	}
	private class CircularLinkedListIterator
		implements java.util.Iterator<E>{
		private Node<E> current = head;
		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public E next() {
			E e = current.element;
			current = current.next;
			return e;
		}
		
		@Override
		public void remove() {
			MyCircularLinkedList.this.remove(indexOf(current.element));
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
