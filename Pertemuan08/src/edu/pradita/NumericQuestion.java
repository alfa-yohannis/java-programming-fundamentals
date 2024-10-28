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
		double expectedValue = Double.valueOf(this.getCorrectAnswer().toString());
		double submittedValue = Double.valueOf(submittedAnswer.toString()); 
		return  expectedValue == submittedValue;
	}

}
