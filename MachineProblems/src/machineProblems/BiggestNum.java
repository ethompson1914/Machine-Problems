package machineProblems;

/*
 * Eric Thompson
 * 9/6/2011
 * Program to calculate the largest number that an OS can reference (most OSs are 64bit or 32bit)
 */
public class BiggestNum {

	private long bigNum;
	private final int base;
	private int power;

	// Constructor
	public BiggestNum() {
		bigNum = 0;
		base = 2;
		power = 0;
	}

	// Calculates the largest integer that the system can reference
	public long calculate() {
		// Infinite loop to keep moving up powers
		while (true) {
			long temp;
			bigNum = (long) Math.pow(base, power);
			temp = (long) Math.pow(base, power + 1);

			// Breaks out when it has reached the largest number
			if (bigNum == temp) {
				break;
			}
			power++;
		}

		// Returns the biggest output
		return bigNum;
	}

	public static void main(String[] stuff) {
		BiggestNum bigNum = new BiggestNum();
		System.out.println("The biggest number this system can reference is: "
				+ bigNum.calculate());
	}
}
