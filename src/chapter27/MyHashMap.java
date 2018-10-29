package chapter27;

import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements MyMap<K, V> {
	// Define the default hash-table size. Must be the power of 2
	private static int DEFAULT_INITIAL_CAPACITY = 4;
	
	// Define the maximum hash-table size. 1 << 30 is same as 2^30
	private static final int MAXIMUN_CAPACITY = 1 << 30;
	
	// Current hash-table capacity. capacity is a power of 2 
	private int capacity;
	
	// Define default load factor
	private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
	
	// Specify a load factor used in the hash-table
	private float loadFactorThreshold;
	
	// The number of entries in the map
	private int size = 0;
	
	// Hash table is an array with each cell being a linked list
	LinkedList<MyMap.Entry<K, V>>[] table;
	
	/** Construct a map with the default capacity and load factor */
	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	/** Construct a map with the specified initial capacity and default load factor */
	public MyHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	/** Construct a map with the specified initial capacity and load factor */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, float loadFactorThreshold) {
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
		removeEntries();
	}


	@Override
	public boolean containsKey(K key) {
		return get(key) != null ? true : false;
	}

	@Override
	public boolean containsValue(V value) {
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				for(Entry<K, V> entry : table[i])
					if(entry.getValue().equals(value))
						return true;
			}
		}
		return false;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new java.util.HashSet<>();
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				for(Entry<K, V> entry : table[i])
					set.add(entry);
			}
		}
		return set;
	}

	@Override
	public V get(K key) {
		int bucketIndex = hash(key.hashCode());
		if(table[bucketIndex] != null) {
			for(Entry<K, V> entry : table[bucketIndex]){
				if(entry.getKey().equals(key))
					return entry.getValue();
			}
		}
		return null;
	}


	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new java.util.HashSet<>();
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				for(Entry<K, V> entry : table[i])
					set.add(entry.getKey());
			}
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		int bucketIndex = hash(key.hashCode());
		if(get(key) != null) {
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for(Entry<K, V> entry : bucket)
				if(entry.getKey().equals(key)) {
					V oldValue = entry.getValue();
					entry.value = value;
					return oldValue;
				}
		}
		
		if(size >= capacity * loadFactorThreshold) {
			if(capacity == MAXIMUN_CAPACITY)
				throw new RuntimeException("Exceeding maximum capacity");
			
			rehash();
		}
		
		if(table[bucketIndex] == null) {
			table[bucketIndex] = new LinkedList<>();
		}
		
		table[bucketIndex].add(new MyMap.Entry<K, V>(key, value));
		
		size++;
		
		return value;
	}

	@Override
	public void remove(K key) {
		int bucketIndex = hash(key.hashCode());
		
		if(table[bucketIndex] != null) {
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for (Entry<K, V> entry : bucket) 
				if(entry.getKey().equals(key)) {
					bucket.remove(entry);
					size--;
					break;
				}
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Set<V> values() {
		Set<V> set = new java.util.HashSet<>();
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				LinkedList<Entry<K, V>> bucket = table[i];
				for (Entry<K, V> entry : bucket) 
					set.add(entry.getValue());
			}
		}
		return set;
	}
	
	private int trimToPowerOf2(int initialCapacity) {
		int capacity = 1;
		while(capacity < initialCapacity) {
			capacity <<= 1;
		}
		return capacity;
	}
	
	private void removeEntries() {
		for(int i = 0; i < capacity; i++) {
			if(table[i] != null) {
				table[i].clear();
			}
		}
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
		Set<Entry<K, V>> set = entrySet();
		capacity <<= 1;
		table = new LinkedList[capacity];
		size = 0;
		
		for(Entry<K, V> entry : set) {
			put(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		
		for (int i = 0; i < capacity; i++) {
			if(table[i] != null && table[i].size() > 0)
				for(Entry<K, V> entry : table[i])
					builder.append(entry);
		}
		
		builder.append("]");
		return builder.toString();
	}
}
