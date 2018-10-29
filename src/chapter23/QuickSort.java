package chapter23;

public class QuickSort {
	public static void main(String[] args) {
		int[] list = {45, 11, 50, 59, 60, 2, 4, 7, 10};
		quickSort(list);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
	}
	/*********************************************************************************/
	public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }
    
    public static void quickSort(int[] list, int first, int last){
        if(last > first){
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }
    
    public static int partition(int[] list, int first, int last){
        int pivot = list[first];
        int low = first + 1;
        int high = last;
        
        while(high > low){
            while(low <= high && list[low] <= pivot)
                low++;
                
            while(low <= high && list[high] > pivot)
                high--;
            
            if(high > low){
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
            
        while(high > first && list[high] >= pivot)
            high--;
        
        if(pivot > list[high]){
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else{
            return first;
        }
    }
}
