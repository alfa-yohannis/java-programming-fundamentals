package edu.pradita;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {

	private List<Object> choices = new ArrayList<>();

	public MultipleChoiceQuestion(String text, Object correctAnswer, List<Object> choices) {
		super(text, correctAnswer);
		this.choices.addAll(choices);
	}

	@Override
	public void display() {
		display(true);
	}

	public void display(boolean withAbc) {
		super.display();
		int code = 97;
		for (Object choice : choices) {
			char head = '-';
			if (withAbc) {
				head = (char) code;
			}
			System.out.println(head + " " + choice);
			code++;
		}

	}

	public List<Object> getChoices() {
		return choices;
	}
}
