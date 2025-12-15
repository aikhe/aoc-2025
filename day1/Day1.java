import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

record RotationValue(char direction, int steps) {
}

public class Day1 {
	public static void main(String[] args) {
		System.out.println("The dial starts by pointing at 50.");

		try {
			Rotation rotation = new Rotation("input.txt", 50, 100);

			rotation.run();
		} catch (FileNotFoundException e) {
			System.out.println("[Error] File not found: " + e.getMessage());
		}
	}
}

class Rotation {
	private final String fileName;
	private int pointer;
	private final int cap;
	private int zeroCount;
	private int pointsVal;

	public Rotation(String fileName, int pointer, int cap) {
		this.fileName = fileName;
		this.pointer = pointer;
		this.cap = cap;
	}

	public void run() throws FileNotFoundException {
		List<String> lines = parseFile();
		for (String line : lines) {
			RotationValue value = parseLine(line);

			if (value.direction() == 'R') {
				pointer = calcRight(value.steps());
			} else if (value.direction() == 'L') {
				pointer = calcLeft(value.steps());
			}

			if (pointer == 0) {
				zeroCount++;
			}

			System.out.println("The dial is rotated " + value.direction() + value.steps()
					+ " to point at " + pointer);
		}

		System.out.println("Part 1 (Final Zeros): " + zeroCount);
		System.out.println("Part 2 (Total Points): " + pointsVal);
	}

	public List<String> parseFile() throws FileNotFoundException {
		List<String> lines = new ArrayList<>();
		File file = new File(fileName);

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (!line.isEmpty()) {
					lines.add(line);
				}
			}
		}

		return lines;
	}

	public RotationValue parseLine(String input) {
		char direction = input.charAt(0);
		int steps = Integer.parseInt(input.substring(1));

		return new RotationValue(direction, steps);
	}

	public int calcRight(int steps) {
		// add points for every loops
		pointsVal += (pointer + steps) / cap;

		return (pointer + steps) % cap;
	}

	public int calcLeft(int steps) {
		int nextStep = steps;

		// total distance to reach 0
		int distance = (pointer == 0) ? cap : pointer;

		// once confident to reach 0 then add points
		if (nextStep >= distance) {
			pointsVal++;
			nextStep -= distance;

			// add points for additional loops
			pointsVal += nextStep / cap;
		}

		// if nextStep is not enough then loop again
		int result = (pointer - steps) % cap;
		if (result < 0) {
			result += cap;
		}

		return result;
	}
}
