package machineProblems;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CryptogramTest {

	Cryptogram crypto1, crypto2, crypto3, crypto4;
	
	@Before
	public void setUp() throws Exception {
		crypto1 = new Cryptogram("the quick brown fox jumps over the lazy dog");
		crypto2 = new Cryptogram("the quick brown fox jumps over the layz dog");
		crypto3 = new Cryptogram("uhe hwman race has one reallt effecuize xeapon and uhau is lawghuer");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCryptogram() {
		assertTrue(crypto1.getMessage().equals("the quick brown fox jumps over the lazy dog"));
		assertTrue(crypto2.getMessage().equals("the quick brown fox jumps over the layz dog"));
		assertTrue(crypto3.getMessage().equals("uhe hwman race has one reallt effecuize xeapon and uhau is lawghuer"));
	}

	@Test
	public void testDecrypt() {
		assertTrue(crypto1.decrypt());
		assertTrue(crypto1.getMessage().equals("the quick brown fox jumps over the lazy dog"));
		assertTrue(crypto2.decrypt());
		assertTrue(crypto2.getMessage().equals("the quick brown fox jumps over the lazy dog"));
		assertTrue(crypto3.decrypt());
		assertTrue(crypto3.getMessage().equals("the human race has one really effective weapon and that is laughter"));
	}

	@Test
	public void testsetHint() {
		crypto3 = new Cryptogram("uhe hwman race has one reallt effecuize xeapon and uhau is lawghuer");
		Date crypto3Time = new Date();
		crypto3.decrypt();
		long millis3 = (new Date()).getTime() - crypto3Time.getTime();
		
		crypto4 = new Cryptogram("uhe hwman race has one reallt effecuize xeapon and uhau is lawghuer");
		crypto4.setHint('t', 'u');
		crypto4.setHint('u', 'w');
		crypto4.setHint('v', 'z');
		Date crypto4Time = new Date();
		crypto4.decrypt();
		long millis4 = (new Date()).getTime() - crypto4Time.getTime();
		
		assertTrue(millis4 < millis3);
	}

}
