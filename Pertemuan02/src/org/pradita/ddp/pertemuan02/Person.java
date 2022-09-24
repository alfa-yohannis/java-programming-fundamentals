package org.pradita.ddp.pertemuan02;

public class Person {

	String name;
	int age;
	String lastName;

	public Person() {
		this.name = "Charlie";
		this.age = 17;
		this.lastName = "Chaplin";
	}

	public Person(String name, String lastName, int age) {
		this.name = name;
		this.age = age;
		this.lastName = lastName;
	}

	public String getFullName() {
		return name + " " + lastName;
	}

	public void introduceMyself() {
		System.out.println("My name is " + this.getFullName() + " and my age is " + this.getAge());
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
