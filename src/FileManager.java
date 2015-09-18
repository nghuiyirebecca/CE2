import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class FileManager {

	private static final boolean NULL_MESSAGE = false;
	private static final String DELETE_MESSAGE = "delete from ";
	private static final boolean SUCCESS_MESSAGE = true;
	private static final String EMPTY_FILE_MESSAGE = " is empty";
	private static final String NO_SUCH_ITEM_MESSAGE = "there is no item containg ";

	
	// Add new input line to the file
	static boolean addToFile(String fileName, String newLine) {
		FileWriter fWriter;
		newLine = newLine.trim();
		try {
			if (!newLine.isEmpty()){
				fWriter = new FileWriter(fileName, true);
				PrintWriter pWriter = new PrintWriter(fWriter);
				pWriter.println(newLine);
				pWriter.close();
			} else {
				return FileManager.NULL_MESSAGE;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There is an IOException");
		}
		return FileManager.SUCCESS_MESSAGE;
	}

	// displays all content in the file
	static void displayFile(String fileName) throws IOException {
		File fileToDisplay = new File(fileName);
		FileReader fReader;
		try {
			fReader = new FileReader(fileToDisplay);
			BufferedReader bReader = new BufferedReader(fReader);
			String data = bReader.readLine();
			if (data == null) {
				System.out.println(fileName + EMPTY_FILE_MESSAGE);
			}
	
			int lineNum = 1;
			while (data != null) {
				System.out.println(lineNum + "." + data);
				data = bReader.readLine();
				lineNum++;
			}
			bReader.close();
	
		} catch (FileNotFoundException e) {
			System.out.println("file is not found");
			e.printStackTrace();
		}
	}

	// deletes a specified line from the file
	static void deleteFromFile(String fileName, int lineToDelete,
			int totalLines) throws IOException {
		File toDelete = new File(fileName);
	
		FileReader fReader = new FileReader(toDelete);
		BufferedReader bReader = new BufferedReader(fReader);
		String str = "";
		String data = bReader.readLine();
		int currLine = 1;
	
		FileManager.clearFile(fileName);
	
		while (currLine <= totalLines) {
			if (currLine != lineToDelete) {
				str = str.concat(data);
				addToFile(fileName, str);
				str = "";
			}
			else{
				System.out.println(FileManager.DELETE_MESSAGE +fileName+": " + data);
			}
			currLine++;
			data = bReader.readLine();
		}
		bReader.close();
	}

	// clears the file of all inputs
	static void clearFile(String fileName) throws IOException {
		FileWriter fWriter = new FileWriter(fileName);
		PrintWriter pWriter = new PrintWriter(fWriter);
		pWriter.print("");
		pWriter.close();
	}

	public static void sortFile(String fileName) throws IOException {
		if (!isSorted(fileName)){
			ArrayList<String> sorted = new ArrayList<String>();
			sorted = convertToArrayList(fileName);
			clearFile(fileName);
			Collections.sort(sorted);
			
			for (String text : sorted){
				addToFile(fileName, text);
			}
		}
	}

	private static ArrayList<String> convertToArrayList(String fileName)
			throws FileNotFoundException, IOException {
		File toSort = new File(fileName);
		FileReader fReader;
		fReader = new FileReader(toSort);
		BufferedReader bReader = new BufferedReader(fReader);
		String item = bReader.readLine();
		ArrayList<String> sorted = new ArrayList<String>();
			
		while (item!=null){
			sorted.add(item);
			item = bReader.readLine();
		}
		bReader.close();
		return sorted;
	}

	public static boolean isSorted(String fileName) throws IOException {
		boolean sorted = true;
		
		ArrayList<String> list = new ArrayList<String>();
		list = convertToArrayList(fileName);

		if (list.isEmpty()){
			sorted = false;
			System.out.println(fileName + EMPTY_FILE_MESSAGE);
		}
	    for (int i = 1; i < list.size(); i++) {
	        if (list.get(i-1).compareTo(list.get(i)) > 0) {
	        	sorted = false;
	        }
	    }
	    return sorted;
	}

	public static int searchFile(String fileName, String word) throws FileNotFoundException, IOException {
		int numLinesMatched = 0;
		ArrayList<String> toSearch = new ArrayList<String>();
		toSearch = convertToArrayList(fileName);
		word = word.trim();
		
		if (toSearch.isEmpty()){
			System.out.println(fileName + EMPTY_FILE_MESSAGE);
			return numLinesMatched;
		}
		
		for (int i = 0; i < toSearch.size(); i++) {
	        if (toSearch.get(i).contains(word)) {
	        	numLinesMatched++;
	        	System.out.println(toSearch.get(i));
	        }
	    }
		
		if (numLinesMatched == 0){
			System.out.println(NO_SUCH_ITEM_MESSAGE + word);
		}
		
		return numLinesMatched;
	}
}
