package edu.pradita;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Exam {

	private List<IQuestion> questions = new ArrayList<>();

	public Collection<IQuestion> getQuestions() {
		return questions;
	}
	
	public void removeQuestion(IQuestion question) {
		questions.remove(question);
	}
	
	public void removeQuestion(int index) {
		questions.remove(index);
	}

	public void addQuestion(IQuestion newQuestion) {
		questions.add(newQuestion);
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);

		for (int lineNum = 1; lineNum <= questions.size(); lineNum++) {
			IQuestion question = questions.get(lineNum - 1);
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
