package edu.pradita;

public class OpenQuestion extends Question {

	public OpenQuestion(String text) {
		super(text, null);
	}

	@Override
	public boolean checkSubmittedAnswer(Object submittedAnswer) {
		return true;
	}

}
