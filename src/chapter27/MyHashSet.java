package chapter27;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashSet<E> implements MySet<E> {

	// Define the default hash-table size.  Must be a power of 2
	private static int DEFAULT_INITIAL_CAPACITY = 4;
	
	// Define the maximum hash-table size.  1 << 30 is same as 2^30
	private static int MAXIMUN_CAPACITY = 1 << 30;
	
	// Current hash-table capacity.  Capacity is a power of 2
	private int capacity;
	
	// Define defaule load factor
	private static float DEFAULT_LOAD_FACTOR = 0.75f;
	
	// Specify a load factor threshold used in the hash table
	private float loadFactorThreshold;
	
	// The number of elements in the set
	private int size = 0;
	
	// Hash table is an array with each cell being a linked list
	private LinkedList<E>[] table;
	
	public MyHashSet() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public MyHashSet(int initialCapacity){
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}
	
	@SuppressWarnings("unchecked")
	public MyHashSet(int initialCapacity, float loadFactorThreshold) {
		if(initialCapacity > MAXIMUN_CAPACITY)
			this.capacity = MAXIMUN_CAPACITY;
		else 
			this.capacity = trimToPowerOf2(initialCapacity);
		this.loadFactorThreshold = loadFactorThreshold;
		table = new LinkedList[capacity];
	}
	
	@Override
	public void clear() {
		size = 0;
		removeElements();
	}

	@Override
	public boolean contains(E e) {
		int bucketIndex = hash(e.hashCode());
		if(table[bucketIndex] != null)
			for(E element : table[bucketIndex])
				if(element.equals(e))
					return true;
		return false;
	}

	@Override
	public boolean add(E e) {
		if(contains(e))
			return false;
		if(size + 1 > capacity * loadFactorThreshold) {
			if(capacity == MAXIMUN_CAPACITY)
				throw new RuntimeException("Exceeding maximun capacity");
			rehash();
		}
		
		int  bucketIndex = hash(e.hashCode());
		
		if(table[bucketIndex] == null)
			table[bucketIndex] = new LinkedList<>();
		
		table[bucketIndex].add(e);
		
		size++;
		
		return true;
	}

	@Override
	public boolean remove(E e) {
		if(!contains(e))
			return false;
		int bucketIndex = hash(e.hashCode());
		
		if(table[bucketIndex] != null) {
			LinkedList<E> bucket = table[bucketIndex];
			for (E element : bucket) 
				if(e.equals(element)) {
					bucket.remove(element);
					break;
				}
		}
		
		size--;
		
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	private int trimToPowerOf2(int initialCapacity) {
		int capacity = 1;
		while(capacity < initialCapacity) {
			capacity <<= 1;
		}
		return capacity;
	}
	
	private void removeElements() {
		for(int i = 0; i < capacity; i++) 
			if(table[i] != null) 
				table[i].clear();
	}
	
	private int hash(int hashCode) {
		return supplementalHash(hashCode) & (capacity - 1);
	}
	
	private static int supplementalHash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}
	
	@SuppressWarnings("unchecked")
	private void rehash() {
		java.util.ArrayList<E> list = setToList();
		capacity <<= 1;
		table = new LinkedList[capacity];
		size = 0;
		
		for(E element : list)
			add(element);
	}
	
	private java.util.ArrayList<E> setToList() {
		java.util.ArrayList<E> list = new java.util.ArrayList<>();
		
		for(int i = 0; i < capacity; i++)
			if(table[i] != null)
				for(E e : table[i])
					list.add(e);
		return list;
	}

	@Override
	public String toString() {
		java.util.ArrayList<E> list = setToList();
		StringBuilder builder = new StringBuilder("[");
		
		for(int i = 0; i < list.size() - 1; i++)
			builder.append(list.get(i) + ", ");
		if(list.size() == 0)
			builder.append("]");
		else
			builder.append(list.get(list.size() - 1) + "]");
		return builder.toString();
	}

	@Override
	public Iterator<E> iterator() {
		return new MyHashSetIterator(this);
	}
	
	private class MyHashSetIterator implements java.util.Iterator<E>{
		private java.util.ArrayList<E> list;
		private int current = 0;
		private MyHashSet<E> set;
		
		public MyHashSetIterator(MyHashSet<E> set) {
			this.set = set;
			list = setToList();
		}
		
		@Override
		public boolean hasNext() {
			return (current < list.size()) ? true : false;
		}

		@Override
		public E next() {
			return list.get(current++);
		}
		
		@Override
		public void remove() {
			set.remove(list.get(current));
			list.remove(current);
		}
	}
}
