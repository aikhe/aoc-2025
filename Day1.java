import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
	/**
	 * Reads all lines from a file and returns them as a List of Strings.
	 *
	 * @param fileName Path to the file
	 * @return List of lines in the file
	 * @throws FileNotFoundException if the file cannot be read
	 */
	public static List<String> readFile(String fileName) throws FileNotFoundException {
		List<String> lines = new ArrayList<>();
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}

		scanner.close();
		return lines;
	}

	public static void main(String[] args) {
		try {
			List<String> lines = readFile("input.txt");
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("[Error] File not found: " + e.getMessage());
		}
	}
}
