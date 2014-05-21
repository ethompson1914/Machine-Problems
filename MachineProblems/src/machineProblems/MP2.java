package machineProblems;

public class MP2 {

	/**
	 * Main encrypt/decrypt method
	 */
	public static void main(String[] args) {
		Message msg = new Message(
				"Hope College is the best school on the planet");
		KeyedRotatedMessage krm;

		System.out.println("Rotated by 3");
		krm = new KeyedRotatedMessage(msg.toString());
		krm.setRotation(3);
		krm.encrypt();
		System.out.println("Rotated only: " + krm.getMessage());
		System.out.println("Pattern: " + krm.getSubstitutionPattern());

		krm = new KeyedRotatedMessage(msg.toString());
		krm.setRotation(3);
		krm.setKey("FoOliShHumANs");
		krm.encrypt();
		System.out.println("Encrypted: " + krm);
		System.out.println("Pattern: " + krm.getSubstitutionPattern());
		if (krm.decrypt()) {
			System.out.println("Message decrypted.  Rotation factor was "
					+ krm.getRotation() + ".");
		} else {
			System.out.println("Message did not decrypt.");
		}
	}
}
