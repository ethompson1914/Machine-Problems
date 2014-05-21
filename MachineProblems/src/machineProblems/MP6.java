package machineProblems;

public class MP6 {

	public static void main(String[] args) {
		Cryptogram message = new Cryptogram("uhe hwman race has one reallt effecuize xeapon and uhau is lawghuer");
		message.decrypt();
		System.out.println(message.getMessage());
	}
}
