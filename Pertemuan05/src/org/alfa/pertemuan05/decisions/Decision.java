package org.alfa.pertemuan05.decisions;

import java.util.Scanner;

public class Decision {

	public static void main(String[] args) {

		/** Decisions, Branching, Conditioning, Flow Control **/
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter your score (0.0 - 100.0): ");

		double score = scanner.nextDouble();

		char mark = 'Z';
//		mark = getIfMark(score);
//		mark = getIfElseMark(score);
//		mark = getNestedIf(score);
		mark = getIfMultipleConditionsMark(score);

		System.out.println("Your mark is '" + mark + "'");

//		if (isExamPassed(mark)) {
//			System.out.println("You have passed the exam");
//		} else {
//			System.out.println("You failed the exam");
//		}
//
		if (isShortExamPassed(mark)) {
			System.out.println("You have passed the exam");
		} else {
			System.out.println("You failed the exam");
		}
	}

	/** If **/
	public static char getIfMark(double score) {
		char mark = 'E';
		if (score >= 80) {
			mark = 'A';
		}
		if (score < 80) {
			mark = 'B';
		}
		if (score < 60) {
			mark = 'C';
		}
		if (score < 20) {
			mark = 'D';
		}
		if (score < 10) {
			mark = 'E';
		}
		return mark;
	}

	/** If Else **/
	public static char getIfElseMark(double score) {
		char mark = 'E';
		if (score >= 80) {
			mark = 'A';
		} else if (score >= 60) {
			mark = 'B';
		} else if (score >= 40) {
			mark = 'C';
		} else if (score >= 20) {
			mark = 'D';
		} else {
			mark = 'E';
		}
		return mark;
	}

	/** Nested if **/
	public static char getNestedIf(double score) {
		char mark = 'E';
		if (score >= 20) {
			mark = 'D';
			if (score >= 40) {
				mark = 'C';
				if (score >= 60) {
					mark = 'B';
					if (score >= 80) {
						mark = 'A';
					}
				}
			}
		}
		return mark;
	}

	/** If with multiple conditions and return **/
	public static char getIfMultipleConditionsMark(double score) {
		if (score >= 80) {
			return 'A';
		}
		if (score >= 60 && score < 80) {
			return 'B';
		}
		if (score >= 40 && score < 60) {
			return 'C';
		}
		if (score >= 20 && score < 40) {
			return 'D';
		}
		return 'E';
	}

	/** switch-case decision **/
	public static boolean isExamPassed(char mark) {
		boolean pass = false;
		switch (mark) {
		case 'A':
			pass = true;
			break;
		case 'B':
			pass = true;
			break;
		case 'C':
			pass = true;
			break;
		default:
			break;
		}
		return pass;
	}

	/** short conditioning **/
	public static boolean isShortExamPassed(char mark) {
		boolean pass = false;
		pass = (mark == 'A' || mark == 'B' || mark == 'C') ? true : false;
		return pass;
	}
}
