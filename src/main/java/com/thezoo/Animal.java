package com.thezoo;

public class Animal {

	private String animalID = null;
	private String animalType = null;
	private String name = null;
	private int age = 0;
	private String diet = null;
	
	
	public Animal() {
		super();
	}


	public String getAnimalID() {
		return animalID;
	}


	public void setAnimalID(String animalID) {
		this.animalID = animalID;
	}


	public String getAnimalType() {
		return animalType;
	}


	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getDiet() {
		return diet;
	}


	public void setDiet(String diet) {
		this.diet = diet;
	}


	@Override
	public String toString() {
		return "Animal " + animalID + ": A " + age + " year old "+ animalType + " named " + name + " who is a " + diet +".";
	}
	
}
