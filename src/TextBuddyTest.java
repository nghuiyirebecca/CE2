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
		testSearch();
	}

	private void testSearch() throws IOException {
		//one or more instance of the word
		FileManager.clearFile(FILENAME);
		FileManager.addToFile(FILENAME, "Reb");
		FileManager.addToFile(FILENAME, "I like dolphins");
		FileManager.addToFile(FILENAME, "Reb2");
		assertSame(2, FileManager.searchFile(FILENAME, "Reb"));
		
		//no instance of the word
		FileManager.clearFile(FILENAME);
		FileManager.addToFile(FILENAME, "Reb");
		FileManager.addToFile(FILENAME, "I like dolphins");
		FileManager.addToFile(FILENAME, "Reb2");
		assertSame(0, FileManager.searchFile(FILENAME, "diapers"));
		
		//empty file
		FileManager.clearFile(FILENAME);
		assertSame(0, FileManager.searchFile(FILENAME, "poop"));
		
		//non alphanumeric characters
		FileManager.clearFile(FILENAME);
		FileManager.addToFile(FILENAME, "Reb!!!!");
		FileManager.addToFile(FILENAME, "I like dolphins@@!");
		FileManager.addToFile(FILENAME, "Reb2 :D");
		assertSame(2, FileManager.searchFile(FILENAME, "!"));
	}

	private void testSort() throws IOException {
		//empty file
		FileManager.clearFile(FILENAME);
		FileManager.sortFile(FILENAME);
		assertEquals(false, FileManager.isSorted(FILENAME));
		
		//already sorted
		FileManager.clearFile(FILENAME);
		FileManager.addToFile(FILENAME, "a");
		FileManager.addToFile(FILENAME, "b");
		FileManager.addToFile(FILENAME, "c");
		FileManager.sortFile(FILENAME);	
		assertEquals(true, FileManager.isSorted(FILENAME));
		
		//sort file
		FileManager.clearFile(FILENAME);
		FileManager.addToFile(FILENAME, "c");
		FileManager.addToFile(FILENAME, "a");
		FileManager.addToFile(FILENAME, "b");
		FileManager.sortFile(FILENAME);	
		assertEquals(true, FileManager.isSorted(FILENAME));
		
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
