import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileManager {

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
				return TextBuddy.NULL_MESSAGE;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There is an IOException");
		}
		return TextBuddy.SUCCESS_MESSAGE;
	}
	
}
