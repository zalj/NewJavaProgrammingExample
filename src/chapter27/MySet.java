package chapter27;

public interface MySet<E> extends java.lang.Iterable<E> {
	/** Clear all elements in the set */
	public void clear();
	
	/** Return true if this set contains specify element */
	public boolean contains(E e);
	
	/** Add a element into the set,  return true if add success */
	public boolean add(E e);
	
	/** Remove a element from the set,  return true if remove success */
	public boolean remove(E e);
	
	/** Return true if the set is empty */
	public boolean isEmpty();
	
	/** Return the number of elements in the set */
	public int size();
}
