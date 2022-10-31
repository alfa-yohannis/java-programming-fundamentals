package edu.pradita;

public class NumericQuestion extends Question {

	public NumericQuestion(String text, int correctAnswer) {
		super(text, correctAnswer);
	}

	public NumericQuestion(String text, double correctAnswer) {
		super(text, correctAnswer);
	}

	@Override
	public boolean checkSubmittedAnswer(Object submittedAnswer) {
		return (double) this.getCorrectAnswer() == Double.valueOf(submittedAnswer.toString());
	}

}
