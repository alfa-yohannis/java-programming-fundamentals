package other.company;

import java.util.Random;

import edu.pradita.IQuestion;

public class RandomMathQuestion implements IQuestion {

	private int a = 0;
	private int b = 0;
	private Random random = new Random();
	private String text;

	public RandomMathQuestion() {
		a = (int) (random.nextDouble() * 100);
		b = (int) (random.nextDouble() * 100);
		text = a + " + " + b + " = ?";
	}
	
	@Override
	public void display() {
		System.out.println(text);

	}

	@Override
	public boolean checkSubmittedAnswer(Object submittedAnswer) {
		int c = Integer.valueOf(submittedAnswer.toString());
		boolean result = (a + b == c);
		return result;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public Object getCorrectAnswer() {
		return a + b;
	}
}

//	private int a = 0;
//	private int b = 0;
//	private Random random = new Random();
//	private String text;
//
//	public RandomMathQuestion() {
//		a = (int) (random.nextDouble() * 10);
//		b = (int) (random.nextDouble() * 10);
//		text = a + " + " + b + " = ?";
//	}
//
//	
//	public boolean checkSubmittedAnswer(Object answer) {
//		int c = Integer.valueOf(answer.toString());
//		boolean result = (a + b == c);
//		return result;
//	}
//
//	
//	public void display() {
//		System.out.println(text);
//	}
//
//	
//	public Object getCorrectAnswer() {
//		return a + b;
//	}
//
//	
//	public String getText() {
//		return text;
//	}

//}
