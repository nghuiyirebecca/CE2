import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileManager {

	private static final boolean NULL_MESSAGE = false;
	private static final String DELETE_MESSAGE = "delete from ";
	private static final boolean SUCCESS_MESSAGE = true;
	
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
				System.out.println(fileName + " is empty");
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

	
	
}