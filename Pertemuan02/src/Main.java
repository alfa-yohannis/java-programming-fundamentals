
public class Main {

	public static void main(String[] args) {
		
		Person person1 = new Person(31, "Donald", "Duck");
		Person person2 = person1;
		person1.setName("Erin");
		person2.introduceMyself();
		
		int x = 1;
		int y = x;
		x = 10;
		System.out.println();
		System.out.println("y, x = " + y + ", " + x);
			
//		int x1 = 1;
//		int x2 = x1;
//		System.out.println("x1 and x2 = " + x1 + " and " + x2);		
//		x1 = 3;
//		System.out.println("x1 and x2 = " + x1 + " and " + x2);
//		
//		
//		Integer y1 = 1;
//		Integer y2 = y1;
//		System.out.println("y1 and y2 = " + y1 + " and " + y2);		
//		y1 = 3;
//		System.out.println("y1 and y2 = " + y1 + " and " + y2);
		
		
		
//		Person person1 = new Person();
//		person1.age = 19;
//		person1.name = "Alice";
//		
//		Person person2 = new Person();
//		person2.age = 20;
//		person2.name = "Bob";
//		
//		System.out.println(person1.name);
//		System.out.println(person2.name);
	}
}
