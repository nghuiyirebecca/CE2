import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;




public class TextBuddy {
	
	private static final String WELCOME_MESSAGE = "Welcome to Textbuddy.";
	private static final String READY_MESSAGE = " is ready for use.";
	private static final String DELETE_MESSAGE = "delete from ";
	private static final String CLEAR_MESSAGE = "all content deleted from ";
	private static final String ADD_MESSAGE = "added to ";
	private static final int SYSTEM_EXIT_SUCCESS = 0;
	static final boolean NULL_MESSAGE = false;
	static final boolean SUCCESS_MESSAGE = true;
	
	public static void main(String[] args) throws IOException {
		String fileName = args[0];
		System.out.println(WELCOME_MESSAGE);
		System.out.println(fileName + READY_MESSAGE);

		FileWriter newFile = new FileWriter(new File(fileName), true);
		executeCommand(fileName);
		newFile.close();
	}

	// To count the total number of data lines in the file
	static int numLines(String fileName) throws IOException {
		File toProcess = new File(fileName);
		FileReader fReader = new FileReader(toProcess);
		BufferedReader bReader = new BufferedReader(fReader);
		int numLines = 0;
		String data = bReader.readLine();
		if (data == null){
			bReader.close();
			return 0;
		}
		while (data != null){
			numLines++;
			data = bReader.readLine();
		}
		bReader.close();
		return numLines;
	}

	// processes the command given to the system
	private static void executeCommand(String fileName) throws IOException {
		Scanner sc = new Scanner(System.in);
		String command = null;
		String data = "";
		int totalLines = numLines(fileName);
		System.out.print("command: ");
		
		while (sc.hasNext()) {
			command = sc.next();
			if (command.equals("add")) {
				data = sc.nextLine();
				if (FileManager.addToFile(fileName, data)){
					totalLines++;
					System.out.println(ADD_MESSAGE + fileName +": " + data);
				}
			} else if (command.equals("display")) {
				displayFile(fileName);
			} else if (command.equals("delete")) {
				int lineToDelete = sc.nextInt();
				deleteFromFile(fileName, lineToDelete, totalLines);
				totalLines--;
			} else if (command.equals("clear")) {
				clearFile(fileName);
				totalLines = 0;
				System.out.println(CLEAR_MESSAGE + fileName);
			} else if (command.equals("exit")) {
				System.exit( SYSTEM_EXIT_SUCCESS);
			}
			System.out.print("command: ");
		}
		sc.close();
	}

	// clears the file of all inputs
	static void clearFile(String fileName) throws IOException {
		FileWriter fWriter = new FileWriter(fileName);
		PrintWriter pWriter = new PrintWriter(fWriter);
		pWriter.print("");
		pWriter.close();
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

		clearFile(fileName);
	
		while (currLine <= totalLines) {
			if (currLine != lineToDelete) {
				str = str.concat(data);
				FileManager.addToFile(fileName, str);
				str = "";
			}
			else{
				System.out.println(DELETE_MESSAGE +fileName+": " + data);
			}
			currLine++;
			data = bReader.readLine();
		}

		bReader.close();
	}

	// displays all content in the file
	private static void displayFile(String fileName) throws IOException {
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
}
