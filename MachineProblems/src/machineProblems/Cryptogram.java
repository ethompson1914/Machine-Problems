package machineProblems;
import java.util.ArrayList;

/**
 * Tries different combinations of letters as keys for solving a cryptogram
 * message. Recursive solution for generating the combinations.
 * 
 * @author Eric
 * 
 */
public class Cryptogram extends Message {

	ArrayList<Character> readableMessage = new ArrayList<Character>();
	ArrayList<Character> secretMessage = new ArrayList<Character>();

	// Stores the messages and initializes the substitution pattern
	public Cryptogram(String message) {
		super(message);
		for (int k='a'; k<='z'; k++) {
			readableMessage.add((Character) (char) k);
			secretMessage.add('0');
		}
	}

	// Calls the recursive decrypt to solve the cryptogram
	public boolean decrypt() {
		return recurse(0);
	}

	// Recursive cryptogram solving algorithm
	private boolean recurse(int letterPosition) {

		// Checks the solution if the substitution pattern is filled
		if (letterPosition == 25) {
			for (int k='z'; k>='a'; k--) {
				if (secretMessage.indexOf((Character) (char) k) == -1) {
					secretMessage.set(letterPosition, (Character) (char) k);
					if (test()) {
						return true;
					}
					secretMessage.set(letterPosition, '0');
					return false;
				}
			}
			return false;
		}
		// Checks if the hint is set. If it is, skip to the next level
		else if (secretMessage.get(letterPosition) != '0') {
			if (recurse(letterPosition + 1)) {
				return true;
			} else {
				return false;
			}
		}
		// Add a letter and move to the next level
		else {
			for (int k='a'; k<='z'; k++) {
				if (secretMessage.indexOf((Character) (char) k) == -1) {
					secretMessage.set(letterPosition, (Character) (char) k);
					if (recurse(letterPosition + 1))
						return true;
				}
			}
			secretMessage.set(letterPosition, '0');
			return false;
		}
	}

	// Allows the user to set hints to help solve the cryptogram faster
	public void setHint(char actualLetter, char cryptedLetter) {
		secretMessage.set(readableMessage.indexOf(actualLetter), cryptedLetter);
	}

	// Sees if a given letter pattern produces a valid solution
	private boolean test() {
		char currentLetter;
		String tempMessage = "";
		for (int k=0; k<getMessage().length(); k++) {
			currentLetter = getMessage().toLowerCase().charAt(k);
			if (currentLetter >= 'a' && currentLetter <= 'z')
				tempMessage += readableMessage.get(secretMessage
						.indexOf(currentLetter));
			else
				tempMessage += getMessage().charAt(k);
		}
		if (new Message(tempMessage).verify()) {
			message = tempMessage;
			return true;
		} else {
			return false;
		}
	}

	// Overrides the toString method
	@Override
	public String toString() {
		return message;
	}

	// Main method for running the program
	public static void main(String[] args) {
		Cryptogram secretMessage = new Cryptogram(
				"uhe hwman race has one reallt effecuize xeapon and uhau is lawghuer");
		secretMessage.decrypt();
	}
}