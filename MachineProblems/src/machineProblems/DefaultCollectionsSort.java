package machineProblems;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DefaultCollectionsSort {

	private long defaultSortTime = 0;
	private long bubbleSortTime = 0;
	private long insertionSortTime = 0;
	private long mergeSortTime = 0;
	private long quickSortTime = 0;
	private ArrayList<Integer> list;

	// Fill an ArrayList with random numbers <10000 to be sorted
	public DefaultCollectionsSort() {
		list = new ArrayList<Integer>();
		for(int k=0; k<10000; k++) {
			Random rand = new Random();
			list.add(new Integer(rand.nextInt(10000)));
		}
	}

	// An algorithm to sort the ArrayList using the built in Collections sort
	public void defaultSort(ArrayList<Integer> list) {
		long startTime = System.currentTimeMillis();

		Collections.sort(list);

		long endTime = System.currentTimeMillis();
		defaultSortTime = endTime - startTime;
	}

	// An algorithm to sort the ArrayList using a bubble sort
	public void bubbleSort(ArrayList<Integer> list) {
		long startTime = System.currentTimeMillis();

		for(int k=1; k<list.size(); k++) {
			for(int j=0; j<list.size()-k; j++) {
				Integer temp = list.get(j);
				list.set(j, list.get(j+1));
				list.set(j+1, temp);
			}
		}

		long endTime = System.currentTimeMillis();
		bubbleSortTime = endTime - startTime;
	}

	// An algorithm to sort the ArrayList using an insertion sort
	public void insertionSort(ArrayList<Integer> list) {
		long startTime = System.currentTimeMillis();

		for (int i=1; i<list.size(); i++){
			Integer j = i;
			Integer B = list.get(i);
			while ((j > 0) && (list.get(j-1) > B)){
				list.set(j, j-1);
				j--;
			}
			list.set(j, B);
		}

		long endTime = System.currentTimeMillis();
		insertionSortTime = endTime - startTime;
	}

	// Helper method for the merge sort
	public void mergeSort_srt(ArrayList<Integer> list,int lo, int n) {
		int low = lo;
		int high = n;
		if (low >= high) {
			return;
		}
	}


	// An algorithm to sort the ArrayList using a merge sort
	public void mergeSort(ArrayList<Integer> list,int lo, int n) {
		long startTime = System.currentTimeMillis();

		int low = lo;
		int high = n;
		if (low >= high) {
			return;
		}

		int middle = (low + high) / 2;
		mergeSort_srt(list, low, middle);
		mergeSort_srt(list, middle + 1, high);
		int end_low = middle;
		int start_high = middle + 1;
		while ((lo <= end_low) && (start_high <= high)) {
			if (list.get(low) < list.get(start_high)) {
				low++;
			} else {
				int temp = list.get(start_high);
				for (int k = start_high- 1; k >= low; k--) {
					list.set(k+1, list.get(k));
				}
				list.set(low, temp);
				low++;
				end_low++;
				start_high++;
			}
		}

		long endTime = System.currentTimeMillis();
		mergeSortTime = endTime - startTime;
	}

	// An algorithm to sort the ArrayList using a quick sort
	public void quickSort(ArrayList<Integer> list, int lo, int hi){
		long startTime = System.currentTimeMillis();

		try {
			//  lo is the lower index, hi is the upper index
			//  of the region of array a that is to be sorted
			int i=lo, j=hi, temp;

			// comparison element x
			int x=list.get((lo+hi)/2);

			//  partition
			do
			{    
				while (list.get(i) < x) i++; 
				while (list.get(j) > x) j--;
				if (i<=j)
				{
					temp=list.get(i);
					list.set(i, j);
					list.set(j, temp);
					i++; j--;
				}
			} while (i<=j);

			//  recursion
			if (lo<j) quickSort(list, lo, j);
			if (i<hi) quickSort(list, i, hi);
		}
		catch(Exception e) {}

		long endTime = System.currentTimeMillis();
		quickSortTime = endTime - startTime;
	}

	/**
	 * @return the list
	 */
	public ArrayList<Integer> getList() {
		return list;
	}

	/**
	 * @return the defaultSortTime
	 */
	public long getDefaultSortTime() {
		return defaultSortTime;
	}

	/**
	 * @return the bubbleSortTime
	 */
	public long getBubbleSortTime() {
		return bubbleSortTime;
	}

	/**
	 * @return the insertionSortTime
	 */
	public long getInsertionSortTime() {
		return insertionSortTime;
	}

	/**
	 * @return the mergeSortTime
	 */
	public long getMergeSortTime() {
		return mergeSortTime;
	}

	/**
	 * @return the quickSortTime
	 */
	public long getQuickSortTime() {
		return quickSortTime;
	}

	public static void main(String[] args) {

		int averageDefaultSortTime = 0;
		int defaultSortZeroCounter = 0;
		
		int averageBubbleSortTime = 0;
		int bubbleSortZeroCounter = 0;
		
		int averageInsertionSortTime = 0;
		int insertionSortZeroCounter = 0;
		
		int averageMergeSortTime = 0;
		int mergeSortZeroCounter = 0;
		
		int averageQuickSortTime = 0;
		int quickSortZeroCounter = 0;

		// Runs 10 times to find the average time for a sort
		for(int k=0; k<100; k++) {

			DefaultCollectionsSort test = new DefaultCollectionsSort();

			// All of the different sorts
			test.defaultSort(test.getList());
			test.bubbleSort(test.getList());
			test.insertionSort(test.getList());
			test.mergeSort(test.getList(), 0, test.getList().size()-1);
			test.quickSort(test.getList(), 0, test.getList().size()-1);

			// Prints the amount of time each sort took
			System.out.println("Run " + (k+1));
			System.out.println("Collections Sort: " + test.getDefaultSortTime());
			System.out.println("Bubble Sort: " + test.getBubbleSortTime());
			System.out.println("Insertion Sort: " + test.getInsertionSortTime());
			System.out.println("Merge Sort: " + test.getMergeSortTime());
			System.out.println("Quick Sort: " + test.getQuickSortTime());
			System.out.println();

			// Removes the times when a sort took 0 milliseconds
			if(test.getDefaultSortTime() == 0)
				defaultSortZeroCounter++;
			else
				averageDefaultSortTime += test.getDefaultSortTime();
			
			if(test.getBubbleSortTime() == 0)
				bubbleSortZeroCounter++;
			else
				averageBubbleSortTime += test.getBubbleSortTime();
			
			if(test.getInsertionSortTime() == 0)
				insertionSortZeroCounter++;
			else
				averageInsertionSortTime += test.getInsertionSortTime();
			
			if(test.getMergeSortTime() == 0)
				mergeSortZeroCounter++;
			else
				averageMergeSortTime += test.getMergeSortTime();
			
			if(test.getQuickSortTime() == 0)
				quickSortZeroCounter++;
			else
				averageQuickSortTime += test.getQuickSortTime();
		}

		// Gives the average of all the tests
		System.out.println("Average Collections Sort: " + averageDefaultSortTime / (100-defaultSortZeroCounter));
		System.out.println("Average Bubble Sort: " + averageBubbleSortTime / (100-bubbleSortZeroCounter));
		System.out.println("Average Insertion Sort: " + averageInsertionSortTime / (100-insertionSortZeroCounter));
		System.out.println("Average Merge Sort: " + averageMergeSortTime / (100-mergeSortZeroCounter));
		System.out.println("Average Quick Sort: " + averageQuickSortTime / (100-quickSortZeroCounter));
	}
}