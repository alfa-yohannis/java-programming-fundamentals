package edu.pradita;

abstract class Question implements IQuestion {

	private String text;
	private Object correctAnswer;

	public Question(String text, Object correctAnswer) {
		this.text = text;
		this.correctAnswer = correctAnswer;
	}

	public void display() {
		System.out.println(this.getText());
	}

	public boolean checkSubmittedAnswer(Object submittedAnswer) {
		if (correctAnswer.equals(submittedAnswer)) {
			return true;
		} else {
			return false;
		}
	}

	public String getText() {
		return text;
	}

	public Object getCorrectAnswer() {
		return correctAnswer;
	}

}
