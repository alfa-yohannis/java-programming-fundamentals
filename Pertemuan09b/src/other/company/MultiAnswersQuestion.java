package other.company;

import java.util.List;

import edu.pradita.MultipleChoiceQuestion;

public class MultiAnswersQuestion extends MultipleChoiceQuestion {

	public MultiAnswersQuestion(String text, List<String> correctAnswers, List<Object> choices) {
		super(text, correctAnswers, choices);
	}

	@Override
	public boolean checkSubmittedAnswer(Object submittedAnswer) {
		String[] answers = submittedAnswer.toString().split(",");
		for (int i = 0; i < answers.length; i++) {
			String answer = answers[i].trim();
			List<String >correctAnwers = 
					(List<String>) getCorrectAnswer();
			if (!correctAnwers.contains(answer)) {
				return false;
			}
		}

		return true;
	}

}
