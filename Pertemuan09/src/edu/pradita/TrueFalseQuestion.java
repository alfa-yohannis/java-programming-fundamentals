package edu.pradita;

import java.util.ArrayList;
import java.util.Arrays;

public class TrueFalseQuestion extends MultipleChoiceQuestion {

	public TrueFalseQuestion(String text, Object correctAnswer) {
		super(text, true, new ArrayList<>(Arrays.asList(true, false)));
	}

	@Override
	public boolean checkSubmittedAnswer(Object submittedAnswer) {
		return (boolean) this.getCorrectAnswer() == Boolean.valueOf(submittedAnswer.toString());
	}
}
