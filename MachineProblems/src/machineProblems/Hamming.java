package machineProblems;

/**
 * Prints a table of all characters 0-9, a-z, and A-Z and their
 * even and odd parity Hamming Code representations
 * @author Eric
 *
 */
public class Hamming {

	public static void printHammingCode(char aChar) {
		String evenParity = calcEvenParity(aChar);
		System.out.print(calcOddParity(evenParity));
		System.out.print(" " + evenParity + "\n");
	}
	
	// Calculates the odd-parity representations based on the even-parity ones
	private static String calcOddParity(String evenParity) {
		int[] bitArray = new int[11];
		for(int k=0; k<11; k++) {
			bitArray[k] = Integer.parseInt(""+evenParity.charAt(k));
		}
		if(bitArray[0] == 1) bitArray[0] = 0;
		else bitArray[0] = 1;
		if(bitArray[1] == 1) bitArray[1] = 0;
		else bitArray[1] = 1;
		if(bitArray[3] == 1) bitArray[3] = 0;
		else bitArray[3] = 1;
		if(bitArray[7] == 1) bitArray[7] = 0;
		else bitArray[7] = 1;
		
		String oddParity = "";
		
		for(int k=0; k<11; k++) {
			oddParity += ""+bitArray[k];
		}
		
		return oddParity;
	}

	// Calculates the even parity representation
	public static String calcEvenParity(char aChar) {
		int[] bitArray = new int[11];
		int intRepresentation = (int) aChar;
		String binaryRepresentation = intToBinary(intRepresentation);
		//System.out.println(binaryRepresentation);
		
		bitArray[2] = Integer.parseInt(""+binaryRepresentation.charAt(0));
		bitArray[4] = Integer.parseInt(""+binaryRepresentation.charAt(1));
		bitArray[5] = Integer.parseInt(""+binaryRepresentation.charAt(2));
		bitArray[6] = Integer.parseInt(""+binaryRepresentation.charAt(3));
		bitArray[8] = Integer.parseInt(""+binaryRepresentation.charAt(4));
		bitArray[9] = Integer.parseInt(""+binaryRepresentation.charAt(5));
		bitArray[10] = Integer.parseInt(""+binaryRepresentation.charAt(6));
		
		// Parity bit 1 (1, 3, 5, 7, 9, 11...)
		int count = 0;
		if(bitArray[2] == 1) count++;
		if(bitArray[4] == 1) count++;
		if(bitArray[6] == 1) count++;
		if(bitArray[8] == 1) count++;
		if(bitArray[10] == 1) count++;
		if(count % 2 == 0)
			bitArray[0] = 0;
		else
			bitArray[0] = 1;
		
		
		// Parity bit 2 (2, 3, 6, 7, 10, 11...)
		count = 0;
		if(bitArray[1] == 1) count++;
		if(bitArray[2] == 1) count++;
		if(bitArray[5] == 1) count++;
		if(bitArray[6] == 1) count++;
		if(bitArray[9] == 1) count++;
		if(bitArray[10] == 1) count++;
		if(count % 2 == 0)
			bitArray[1] = 0;
		else
			bitArray[1] = 1;
		
		// Parity bit 3 (4, 5, 6, 7, 12, 13, 14, 15...)
		count = 0;
		if(bitArray[3] == 1) count++;
		if(bitArray[4] == 1) count++;
		if(bitArray[5] == 1) count++;
		if(bitArray[6] == 1) count++;
		if(count % 2 == 0)
			bitArray[3] = 0;
		else
			bitArray[3] = 1;
		
		// Parity bit 4 (8,9,10,11,12,13,14,15,24,25,26...)
		count = 0;
		if(bitArray[7] == 1) count++;
		if(bitArray[8] == 1) count++;
		if(bitArray[9] == 1) count++;
		if(bitArray[10] == 1) count++;
		if(count % 2 == 0)
			bitArray[7] = 0;
		else
			bitArray[7] = 1;
		
		String evenParity = "";
		
		for(int k=0; k<11; k++) {
			evenParity += ""+bitArray[k];
		}
		
		return evenParity;
	}
	
	// Converts a number to its binary representation
	private static String intToBinary(int num) {

		String tempString = "";

		while (num > 0) {
			if (num % 2 == 1) {
				tempString = "1" + tempString;
				num /= 2;
			} else {
				tempString = "0" + tempString;
				num /= 2;
			}
		}
		
		// Adds 0s to the front of the string if it isn't 7 bits long
		while(tempString.length() < 7) {
			tempString = "0" + tempString;
		}

		return tempString;
	}

	public static void main(String[] args) {
		char ch;
		System.out.println(" *** The Hamming Code Table ***");
		System.out.println(" Odd Even");
		System.out.println(" ----------- -----------");
		for (ch = '0'; ch <= '9'; ch++) {
			System.out.print(" " + (char) ch + " is ");
			Hamming.printHammingCode(ch);
		}
		for (ch = 'A'; ch <= 'Z'; ch++) {
			System.out.print(" " + (char) ch + " is ");
			Hamming.printHammingCode(ch);
		}
		for (ch = 'a'; ch <= 'z'; ch++) {
			System.out.print(" " + (char) ch + " is ");
			Hamming.printHammingCode(ch);
		}
	}
}
