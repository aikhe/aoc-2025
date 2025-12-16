import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

record RangeValue(long initial, long last) {
}

record Split(String firstSlice, String secondSlice) {
}

public class Day2 {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Hello, Day 2!");

		Range range = new Range("input.txt");
		// Range range = new Range("sample-input.txt");
		range.run();
	}
}

class Range {
	private final String fileName;
	private long total;

	public Range(String fileName) {
		this.fileName = fileName;
	}

	public void run() throws FileNotFoundException {
		List<String> ranges = parseFile();

		for (String range : ranges) {
			RangeValue rangeValues = parseRange(range);

			for (long num = rangeValues.initial(); num <= rangeValues.last(); num++) {
				// checks if number has two sequence
				if (checkIfRepeatedTwice(String.valueOf(num))) {
					Split splitValues = splitNumber(String.valueOf(num));

					// now test if both sequence are equal
					if (splitValues.firstSlice().equals(splitValues.secondSlice())) {
						System.out.println("Number: " + num + ", " + splitValues.firstSlice() + "-" + splitValues.secondSlice()
								+ ", Valid Range | " + "" + "Valid ID");

						total += num;
					} else {
						System.out.println("Number: " + num + ", " + splitValues.firstSlice() + "-" + splitValues.secondSlice()
								+ ", Valid Range | " + "" + "Invalid ID");
					}
				} else {
					System.out.println("Number: " + num + ", Invalid Range");
				}
			}
		}

		System.out.println("Total: " + total);
	}

	private List<String> parseFile() throws FileNotFoundException {
		List<String> ranges = new ArrayList<>();
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		// use regex to get each reange separated by comma
		scanner.useDelimiter("\\s*,\\s*");

		while (scanner.hasNext()) {
			String range = scanner.next().trim();
			ranges.add(range);
		}

		scanner.close();
		return ranges;
	}

	private RangeValue parseRange(String range) {
		String[] parts = range.split("-");

		long initial = Long.parseLong(parts[0].trim());
		long last = Long.parseLong(parts[1].trim());
		return new RangeValue(initial, last);
	}

	private boolean checkIfRepeatedTwice(String range) {
		if (range.length() % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	private Split splitNumber(String num) {
		int mid = num.length() / 2;

		return new Split(num.substring(0, mid), num.substring(mid));
	}
}
