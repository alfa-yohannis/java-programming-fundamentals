package org.pradita.ddp.pertemuan02;

public class Main {

	public static void main(String[] args) {
		
		Person person1 = new Person();
		Person person2 = new Person("Alice", "Wonderland", 31);
		
		System.out.println("Person1's name is " + person1.getName() + ",age " + person1.getAge());
		System.out.println("Person2's name is " + person2.getName() + ", age " + person2.getAge());
		
		System.out.println("Person1's fullname is " + person1.getFullName() + ",age " + person1.getAge());
		System.out.println("Person2's fullname is " + person2.getFullName() + ", age " + person2.getAge());
		
		person1.introduceMyself();
		
		person2.setName("Bob");
		person2.introduceMyself();
	}

}
