
public class Person {

	int age;
	String name;
	String lastName;

	Person(int age, String name, String lastName) {
		this.age = age;
		this.name = name;
		this.lastName = lastName;
	}

	public void setName(String newName) {
		this.name = newName;
	}
	
	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public String getFullName() {
		return name + " " + lastName;
	}

	public void introduceMyself() {
		System.out.println("My name is " + getFullName());
	}

}
