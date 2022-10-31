package edu.pradita;

public interface IQuestion {

	public void display();

	public boolean checkSubmittedAnswer(Object submittedAnswer);

	public String getText();

	public Object getCorrectAnswer();

}
