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
		testSort();
	}

	private void testSort() throws IOException {
		//already sorted
		FileManager.clearFile(FILENAME);
		FileManager.addToFile(FILENAME, "a");
		FileManager.addToFile(FILENAME, "b");
		FileManager.addToFile(FILENAME, "c");
		FileManager.sortFile(FILENAME);	
		assertEquals(true, FileManager.isSorted());
	}

	private void testClear() throws IOException {
		FileManager.clearFile(FILENAME);
		assertEquals(0, TextBuddy.numLines(FILENAME));
	}

	public void testAdd() throws IOException  {
		FileManager.addToFile(FILENAME, "byebye");
		FileManager.addToFile(FILENAME, "abba");
		assertEquals(2, TextBuddy.numLines(FILENAME));
		
		//add empty string
		FileManager.addToFile(FILENAME, "");
		assertEquals(2, TextBuddy.numLines(FILENAME));
	}
	
	public void testDelete() throws IOException{
		FileManager.deleteFromFile(FILENAME, 1, 2);
		assertEquals(1, TextBuddy.numLines(FILENAME));
		
	}
	
	
	
}
