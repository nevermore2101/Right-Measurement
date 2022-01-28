package classes;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	private int age;
	private String name;
	private char gender;
	private double weight, height;
	
	public Person(String name, int age, char gender, double weight, double height) {
		super();
		this.age = age;
		this.name = name;
		this.gender = gender;
		this.weight = weight;
		this.height = height;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nAge: " + age + "\nGender: " + gender + "\nWeight: " + weight + "kg\nHeight: " + height/100+ "m";
	}
}
