package chapter23;


public class HeapSort {
	/** Heap sort method */
	public static <E extends Comparable<E>> void headSort(E[] list) {
		// Create a Heap of integers
		Heap<E> heap = new Heap<>(list);
		
		// Remove elements from the heap
		for (int i = list.length - 1; i >= 0; i--) {
			list[i] = heap.remove(); 
		}
	}
	
	public static void main(String[] args) {
		Integer[] list = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53};
		long heapStartTime = System.currentTimeMillis();
		headSort(list);
		System.out.println("Heap Sort cost " + (System.currentTimeMillis() - heapStartTime) + " millseconds");
		int[] newList = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53};
		long quickStartTime = System.currentTimeMillis();
		QuickSort.quickSort(newList);
		System.out.println("Quick Sort cost " + (System.currentTimeMillis() - quickStartTime) + " millseconds");
	}

}
