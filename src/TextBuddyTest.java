import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class TextBuddyTest {

	private static final String FILENAME = "mytextfile.txt";
	@Test
	public void test() throws IOException {
		testClear();
		testAdd();
		testDelete();
	}
	
	private void testClear() throws IOException {
		TextBuddy.clearFile(FILENAME);
		assertEquals(0, TextBuddy.numLines(FILENAME));
		
	}

	public void testAdd() throws IOException  {
		TextBuddy.addToFile(FILENAME, "hello");
		TextBuddy.addToFile(FILENAME, "byebye");
		assertEquals(2, TextBuddy.numLines(FILENAME));
	}
	
	public void testDelete() throws IOException{
		TextBuddy.deleteFromFile(FILENAME, 1, 2);
		assertEquals(1, TextBuddy.numLines(FILENAME));
		
	}

	
}
