package machineProblems;

public class FloatingPointTest {

	public static void main(String[] args) throws Exception
	{
	 FloatingPoint flpt = new FloatingPoint(32);
	 FloatingPoint wide = new FloatingPoint(64);
	 
	 flpt.setValue(-18.625);
	 System.out.println("Floating point value should be 0.5: "+flpt.getValue());
	 System.out.println("As a bitstring: "+flpt.asBitString());
	 System.out.println("Signed integer value should be 1056964608: "+flpt.asSignedInteger());
	 System.out.println("Unsigned integer value should be 1056964608: "+flpt.asUnsignedInteger());
	  
	 /*flpt.setValue(9.625);
	 System.out.println("Floating point value should be 9.625: "+flpt.getValue());
	 System.out.println("As a bitstring: "+flpt.asBitString());
	 System.out.println("Signed integer value should be 1092222976: "+flpt.asSignedInteger());
	 System.out.println("Unsigned integer value should be 1092222976: "+flpt.asUnsignedInteger());
	  
	 wide.setValue(-7.25);
	 System.out.println("Floating point value should be -7.25: "+wide.getValue());
	 System.out.println("As a bitstring: "+wide.asBitString());
	 System.out.println("Signed integer value should be -2147483648: "+wide.asSignedInteger());
	 System.out.println("Unsigned integer value should be 2147483647: "+wide.asUnsignedInteger());	  */
	}
}
