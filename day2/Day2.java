import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

record RangeValue(long initial, long last) {
}

record Split(String firstSlice, String secondSlice) {
}

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
				if (checkIfCanSlice(String.valueOf(num))) {
					Map<Integer, List<String>> splitValues = splitNumber(String.valueOf(num));

					System.out.println(splitValues);
					// now test if both sequence are equal
					// if (splitValues.firstSlice().equals(splitValues.secondSlice())) {
					// System.out.println("Number: " + num + ", " + splitValues.firstSlice() + "-" +
					// splitValues.secondSlice()
					// + ", Even Range | " + "" + "Invalid ID");
					//
					// total += num;
					// } else {
					// System.out.println("Number: " + num + ", " + splitValues.firstSlice() + "-" +
					// splitValues.secondSlice()
					// + ", Even Range | " + "" + "Valid ID");
					// }
				} else {

					System.out.println("Number: " + num + ", Odd Range");
				}
			}
		}

		System.out.println("Total Invalid ID: " + total);
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

	private boolean checkIfCanSlice(String range) {
		if (range.length() % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	private Map<Integer, List<String>> splitNumber(String num) {
		Map<Integer, List<String>> slices = new HashMap<>();
		int mid = num.length() / 2;

		for (int sequenceNum = 1; sequenceNum <= mid; sequenceNum++) {
			int sequencePointer = 0;

			if (num.length() % sequenceNum != 0) {
				continue;
			}

			for (sequencePointer = 0; sequencePointer + sequenceNum <= num.length(); sequencePointer += sequenceNum) {
				slices.computeIfAbsent(sequenceNum, k -> new ArrayList<>())
						.add(num.substring(sequencePointer, sequencePointer + sequenceNum));
				// System.out.println("Sequence Pointer: " + sequencePointer);
			}

			sequencePointer = 0;
		}

		return slices;
	}
}
