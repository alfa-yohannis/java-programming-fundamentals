package other.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.pradita.Exam;
import edu.pradita.FilledInQuestion;
import edu.pradita.IQuestion;
import edu.pradita.MultipleChoiceQuestion;
import edu.pradita.OpenQuestion;

public class Main {

	public static void main(String[] args) {

		Exam exam = new Exam();

//		IQuestion q1 = new FilledInQuestion
//				("The capital of Indonesia is ____", "Jakarta");
//		exam.addQuestion(q1);
//		
//		IQuestion q2 = new OpenQuestion("Apa pendapatmu tentang Indonesia?");
//		exam.addQuestion(q2);
//		
//		IQuestion q3 = new RandomMathQuestion();
//		exam.addQuestion(q3);
//		
//		for (int i = 1; i <= 5; i++) {
//			exam.addQuestion(new RandomMathQuestion());
//		}

		String text = "Kota-kota di Indonesia";
		List<String> correctAnswers = new ArrayList<String>(
				Arrays.asList(new String[] { "Surabaya", "Jakarta" }));
		List<Object> options = new ArrayList<Object>(
				Arrays.asList(new String[] { "Tokyo", 
						"Surabaya", "Jakarta" }));
		
		IQuestion q4 = new MultiAnswersQuestion(text, 
				correctAnswers, options);
		
		exam.addQuestion(q4);

		exam.start();

	}

}
