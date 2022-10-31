package other.company;

import edu.pradita.Exam;
import edu.pradita.FilledInQuestion;
import edu.pradita.IQuestion;

public class Main {

	public static void main(String[] args) {
		
		Exam exam = new Exam();
		
		IQuestion q1 = new FilledInQuestion("The capital of Indonesia is ____", "Jakarta");
		exam.addQuestion(q1);
		
		for (int i = 1; i <= 5; i++) {
			exam.addQuestion(new RandomMathQuestion());
		}
		
		exam.start();
	}

}
