import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Hello, Day 2!");

		// Range range = new Range("input.txt");
		Range range = new Range("sample-input.txt");
		range.run();
	}
}

class Range {
	private final String fileName;

	public Range(String fileName) {
		this.fileName = fileName;
	}

	public void run() throws FileNotFoundException {
		List<String> ranges = parseFile();

		int id = 0;
		for (String range : ranges) {
			if (checkIfRepeatedTwice(range)) {
				System.out.println("Range " + id + ": " + range + ", yes");
			} else {
				System.out.println("Range " + id + ": " + range + ", no");
			}

			id++;
		}
	}

	private List<String> parseFile() throws FileNotFoundException {
		List<String> ranges = new ArrayList<>();
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		// use reges to get each reange separated by comma
		scanner.useDelimiter("\\s*,\\s*");

		while (scanner.hasNext()) {
			String range = scanner.next().trim();
			ranges.add(range);
		}

		scanner.close();
		return ranges;
	}

	// private int parseRange() {
	//
	// }

	private boolean checkIfRepeatedTwice(String range) {
		if (range.length() % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}
}
