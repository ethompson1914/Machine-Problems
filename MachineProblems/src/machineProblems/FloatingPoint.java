package machineProblems;

/*
 * Eric Thompson
 * Converts a decimal number to IEEE 754 format
 * or as a binary number either signed or unsigned
 * I collaborated some with John Stathakis and Hsiang Lin
 */
public class FloatingPoint {

	// Declare variables

	// The length of the bit representation for a number, must be either 32 or
	// 64
	private double width = 0;

	// The value
	private double value;

	// Array to hold the bit representation of the number
	private int[] bitArray;

	// Constructor, throws exception if bit parameter is not 32 or 64
	FloatingPoint(int aWidth) throws IllegalArgumentException {
		if (aWidth == 32 || aWidth == 64) {
			width = aWidth;
			bitArray = new int[(int) width];
		} else {
			throw new IllegalArgumentException(
					"The parameter must be either 32 or 64.");
		}
	}

	// Sets the new value of the number to represent
	public void setValue(double newValue) {

		value = newValue;
		inFP(newValue);
	}

	// Returns bit representation length of the number
	public double getWidth() {
		return width;
	}

	// Gets the value stored in the object
	public double getValue() {
		return value;
	}

	// Converts a number to its binary representation
	public String intToBinary(int num) {

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

		return tempString;
	}

	private String checkForZeroes(String bits) {
		while (bits.length() < 8) {
			bits = "0" + bits;
		}
		return bits;
	}

	// Converts numbers greater than 1 to binary
	public String calcNonDecimal(int numToCalc) {
		int num = Math.abs(numToCalc);
		String bitString = "";
		while (num > 0) {
			if (num % 2 == 1) {
				bitString = "1" + bitString;
				num--;
			} else {
				bitString = "0" + bitString;
			}
			num /= 2;
		}
		return bitString;
	}

	// Converts a fraction to binary
	public String calcDecimal(int numToCalc) {
		int count = 0;
		String bitString = "";
		String numLength = numToCalc + "";
		double num = numToCalc / (Math.pow(10, numLength.length()));
		while (num != 1 && count < 32) {
			num *= 2;
			if (num > 1) {
				bitString = bitString + "1";
				num = (num % 1);
			} else if (num == 1) {
				bitString = bitString + "1";
				break;
			} else {
				bitString = bitString + "0";
			}
			count++;
		}
		return bitString;
	}

	// Takes in a binary string and puts it into an array
	private void putBinaryIntoArray(String bits, int[] array, int startIndex) {
		for (int x = startIndex; x < bits.length() + startIndex && x < width; x++) {
			if (bits.charAt(x - startIndex) == '1') {
				array[x] = 1;
			} else {
				array[x] = 0;
			}
		}
	}

	private String inFP(double number) {

		// Figures out what the sign bit should be
		if (number >= 0) {
			bitArray[0] = 0;
		} else {
			bitArray[0] = 1;
		}

		// Splits the string up into separate parts
		String tempString = "" + number;
		String[] nums = tempString.split("\\.");
		int rightSide = Integer.parseInt(nums[1]);
		int leftSide = (int) number;
		String exponent = calcNonDecimal(leftSide);
		String decimalBitString = calcDecimal(rightSide);
		String mantissa = exponent + decimalBitString;

		// Removes the sign bit
		mantissa = mantissa.substring(1);

		// Calculates what the exponent will be
		int numExponents = exponent.length() - 1;
		int numDecimalExponents = decimalBitString.indexOf("1");
		int finalExponents;
		if (numExponents >= 0) {
			if (width == 32) {
				finalExponents = numExponents + 127;
			} else {
				finalExponents = numExponents + 1023;
			}
		} else {
			if (width == 32) {
				finalExponents = 127 - (numDecimalExponents + 1);
			} else {
				finalExponents = 1032 - (numDecimalExponents + 1);
			}
		}

		// Adds zeroes if needed for the exponent
		String exponentBits = checkForZeroes(calcNonDecimal(finalExponents));
		putBinaryIntoArray(exponentBits, bitArray, 1);
		if (width == 32) {
			putBinaryIntoArray(mantissa, bitArray, 9);
		} else {
			putBinaryIntoArray(mantissa, bitArray, 12);
		}
		return "" + bitArray;
	}

	// Returns the bit string representation of the number
	public String asBitString() {

		String tempString = "";

		for (int k = 0; k < width; k++) {
			tempString += "" + bitArray[k];
		}

		return tempString;
	}

	// Returns the integer as a signed integer calculated from its IEEE format
	public long asSignedInteger() {

		int signedInt = 0;
		for (int k = 0; k <= bitArray.length - 1; k++) {
			signedInt = (int) (signedInt + bitArray[k]
					* (Math.pow(2, Math.abs((bitArray.length - 1) - k))));
		}
		if (bitArray[0] == 1) {
			signedInt *= (-1);
			signedInt -= 1;
		}
		return signedInt;

	}

	// Returns the integer as an unsigned integer calculated from its IEEE
	// format
	public int asUnsignedInteger() {

		int unsignedInt = 0;
		for (int k = 0; k <= bitArray.length - 1; k++) {
			unsignedInt = (int) (unsignedInt + bitArray[k]
					* (Math.pow(2, Math.abs((bitArray.length - 1) - k))));
		}

		return unsignedInt;
	}
}
