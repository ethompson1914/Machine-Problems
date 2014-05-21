package machineProblems;

public class MP1 {



	/**
	 * Main encrypt/decrypt method
	 */
	public static void main(String[] args) {
		Message message = new Message("EOXH MHDQV");
		RotatedMessage rotated;

		if (message.verify()) {
			System.out.println("All words are in the dictionary.");
		} else {
			System.out.println("Some words are NOT in the dictionary.");
		}

		System.out.println("Boundary Tests:");
		System.out.print("\tAt 0: ");
		rotated = new RotatedMessage(message.toString());
		rotated.setRotation(0);
		rotated.encrypt();
		System.out.println(rotated);
		System.out.print("\tAt 26: ");
		rotated = new RotatedMessage(message.toString());
		rotated.setRotation(26);
		rotated.encrypt();
		System.out.println(rotated);

		rotated = new RotatedMessage(message.toString());
		/*rotated.setRotation(3);
		System.out.println("Rotated by " + rotated.getRotation());
		rotated.encrypt();
		System.out.println("Rotated: " + rotated);*/
		if (rotated.decrypt()) {
			System.out.println("Message decrypted.  Rotation factor was "
					+ rotated.getRotation() + ".");
			System.out.println("The message is: " + rotated.getMessage());
		} else {
			System.out.println("Message did not decrypt.");
		}
	}
}
