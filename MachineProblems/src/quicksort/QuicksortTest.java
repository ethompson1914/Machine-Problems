package quicksort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Every once and awhile either the recursive version or the threaded
 * version throws an ArrayIndexOutOfBoundsException. The program compiles
 * fine and if that problem occurs, just rerun the program a few times and
 * it will fix itself.
 * @author Eric
 *
 */

public class QuicksortTest {

	private static long recursiveTimeRandom = 0;
	private static long recursiveTimeSortedLH = 0;
	private static long recursiveTimeSortedHL = 0;
	private static long threadedTimeRandom = 0;
	private static long threadedTimeSortedLH = 0;
	private static long threadedTimeSortedHL = 0;

	private static long averageRecursiveTimeRandom = 0;
	private static long averageRecursiveTimeSortedLH = 0;
	private static long averageRecursiveTimeSortedHL = 0;
	private static long averageThreadedTimeRandom = 0;
	private static long averageThreadedTimeSortedLH = 0;
	private static long averageThreadedTimeSortedHL = 0;

	private ArrayList<Integer> lowToHighList;
	private ArrayList<Integer> highToLowList;
	private ArrayList<Integer> randomList;

	public QuicksortTest() {

		// Creates a list with random numbers, sorted lowest to
		// highest, and sorted highest to lowest.
		randomList = new ArrayList<Integer>();
		lowToHighList = new ArrayList<Integer>();
		highToLowList = new ArrayList<Integer>();
		for(int k=0; k<5000000; k++) {
			Random rand = new Random();
			randomList.add(new Integer(rand.nextInt(10000)));
			
			lowToHighList.add(new Integer(rand.nextInt(10000)));
			
			highToLowList.add(new Integer(rand.nextInt(10000)));
		}
		Collections.sort(lowToHighList);
		Collections.sort(highToLowList, Collections.reverseOrder());		
	}

	/**
	 * @return the lowToHighList
	 */
	public ArrayList<Integer> getLowToHighList() {
		return lowToHighList;
	}

	/**
	 * @return the highToLowList
	 */
	public ArrayList<Integer> getHighToLowList() {
		return highToLowList;
	}

	/**
	 * @return the randomList
	 */
	public ArrayList<Integer> getRandomList() {
		return randomList;
	}

	public static void main(String[] args) {

		for(int k=0; k<20; k++) {
			QuicksortTest qt = new QuicksortTest();
			RecursiveQuicksort rq = new RecursiveQuicksort();
			ThreadedQuicksort tq = new ThreadedQuicksort();

			// Tests cases of sorts for each method of sorting
			rq.sort(qt.getRandomList());
			recursiveTimeRandom = rq.getTotalSortTime();
			rq.sort(qt.getLowToHighList());
			recursiveTimeSortedLH = rq.getTotalSortTime();
			rq.sort(qt.getHighToLowList());
			recursiveTimeSortedHL = rq.getTotalSortTime();

			tq.sort(qt.getRandomList());
			threadedTimeRandom = rq.getTotalSortTime();
			tq.sort(qt.getLowToHighList());
			threadedTimeSortedLH = rq.getTotalSortTime();
			tq.sort(qt.getHighToLowList());
			threadedTimeSortedHL = rq.getTotalSortTime();

			averageRecursiveTimeRandom += recursiveTimeRandom;
			averageRecursiveTimeSortedLH += recursiveTimeSortedLH;
			averageRecursiveTimeSortedHL += recursiveTimeSortedHL;
			averageThreadedTimeRandom += threadedTimeRandom;
			averageThreadedTimeSortedLH += threadedTimeSortedLH;
			averageThreadedTimeSortedHL += threadedTimeSortedHL;

			// Outputs data of the test run
			System.out.println("Run " + (k+1));
			System.out.println("Time for recursive quicksort on random list: " + recursiveTimeRandom + " milliseconds");
			System.out.println("Time for recursive quicksort on sorted LH list: " + recursiveTimeSortedLH + " milliseconds");
			System.out.println("Time for recursive quicksort on sorted HL list: " + recursiveTimeSortedHL + " milliseconds");

			System.out.println("Time for threaded quicksort on random list: " + threadedTimeRandom + " milliseconds");
			System.out.println("Time for threaded quicksort on sorted LH list: " + threadedTimeSortedLH + " milliseconds");
			System.out.println("Time for threaded quicksort on sorted HL list: " + threadedTimeSortedHL + " milliseconds");
			System.out.println();
		}
		
		// Prints out the average times for both the recursive version and the threaded version
		System.out.println("Averages:");
		System.out.println("Average recursive random time after 20 runs: " + (averageRecursiveTimeRandom/20)+
				" milliseconds");
		System.out.println("Average recursive LH time after 20 runs: " + (averageRecursiveTimeSortedLH/20)+
				" milliseconds");
		System.out.println("Average recursive HL time after 20 runs: " + (averageRecursiveTimeSortedHL/20)+
				" milliseconds");
		System.out.println();

		System.out.println("Average threaded random time after 20 runs: " + (averageThreadedTimeRandom/20)+
				" milliseconds");
		System.out.println("Average threaded LH time after 20 runs: " + (averageThreadedTimeSortedLH/20)+
				" milliseconds");
		System.out.println("Average threaded HL time after 20 runs: " + (averageThreadedTimeSortedHL/20)+
				" milliseconds");
	}
}
