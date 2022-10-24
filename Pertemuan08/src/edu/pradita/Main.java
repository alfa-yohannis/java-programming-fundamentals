package edu.pradita;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		List<Question> questions = new ArrayList<>();

		questions.add(new FilledInQuestion("He is __ farmer.", "a"));
		questions.add(new NumericQuestion("1 + 1 = ?", 2));
		questions.add(new OpenQuestion("What is your plan for the next semester?"));
		questions.add(new MultipleChoiceQuestion("_____ in the Wonderland.", //
				"Alice", //
				new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"))//
		));
		questions.add(new TrueFalseQuestion("Is today Tuesday?", true));

		Scanner scanner = new Scanner(System.in);

		for (int lineNum = 1; lineNum <= questions.size(); lineNum++) {
			Question question = questions.get(lineNum - 1);
			System.out.println("Question " + lineNum + ":");
			question.display();
			System.out.print("Your answer: ");
			String answer = scanner.nextLine().trim();
			boolean result = question.checkSubmittedAnswer(answer);
			System.out.println("Result: " + result);
			System.out.println();
		}

		scanner.close();

	}

}
