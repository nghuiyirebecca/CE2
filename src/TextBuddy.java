import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;




public class TextBuddy {
	
	private static final String WELCOME_MESSAGE = "Welcome to Textbuddy.";
	private static final String READY_MESSAGE = " is ready for use.";
	private static final String CLEAR_MESSAGE = "all content deleted from ";
	private static final String ADD_MESSAGE = "added to ";
	private static final int SYSTEM_EXIT_SUCCESS = 0;
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
				FileManager.displayFile(fileName);
			} else if (command.equals("delete")) {
				int lineToDelete = sc.nextInt();
				FileManager.deleteFromFile(fileName, lineToDelete, totalLines);
				totalLines--;
			} else if (command.equals("clear")) {
				FileManager.clearFile(fileName);
				totalLines = 0;
				System.out.println(CLEAR_MESSAGE + fileName);
			} else if (command.equals("exit")) {
				System.exit( SYSTEM_EXIT_SUCCESS);
			}
			System.out.print("command: ");
		}
		sc.close();
	}
}
