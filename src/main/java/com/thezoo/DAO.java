package com.thezoo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "root";

	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;

	static Scanner sc = new Scanner(System.in);

	public static void connToDB() {

		try {
			
			Class.forName(JDBC_DRIVER);
			
			System.out.println("Trying to connect to the Database.");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to Database.");

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Failed to connect to the Database.");
			e.printStackTrace();
		}

		System.out.println();

	}

	public static void readFromDB() {

		connToDB();

		ArrayList<Animal> myAnimals = new ArrayList<>();

		try {
			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM assignments.zoo_animals;");

			while (RES_SET.next()) {

				Animal animalInDB = new Animal();

				animalInDB.setAnimalID(RES_SET.getString("animal_id"));
				animalInDB.setAnimalType(RES_SET.getString("animal_species"));
				animalInDB.setName(RES_SET.getString("name"));
				animalInDB.setAge(RES_SET.getInt("age"));
				animalInDB.setDiet(RES_SET.getString("diet"));

				myAnimals.add(animalInDB);

			}
			for (Animal animal : myAnimals) {

				System.out.println(animal.toString());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void writeToDB(Animal animal) {

		Animal animalToAdd = new Animal();

		animalToAdd = animal;

		connToDB();

		try {
			PREP_STMT = CONN.prepareStatement(insertToDB);

			PREP_STMT.setString(1, animalToAdd.getAnimalType());
			PREP_STMT.setString(2, animalToAdd.getName());
			PREP_STMT.setInt(3, animalToAdd.getAge());
			PREP_STMT.setString(4, animalToAdd.getDiet());

			PREP_STMT.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static String insertToDB = "INSERT INTO `assignments`.`zoo_animals`" + "(animal_species, name, age, diet)"
			+ " VALUES " + "(?, ?, ?, ?)";

	private static Animal aboutTheAnimal() {

		Animal animalToAdd = new Animal();

		System.out.println("What is the species of the animal?");
		animalToAdd.setAnimalType(sc.nextLine());
		System.out.println("What is the animal's name?");
		animalToAdd.setName(sc.nextLine());
		System.out.println("How old is the animal?");
		animalToAdd.setAge(Integer.parseInt(sc.nextLine()));
		System.out.println("Is the animal a Carnivore, Herbivore, and Omnivore?");
		animalToAdd.setDiet(sc.nextLine());

		return animalToAdd;
	}

	public static void deleteFromDB(int animalId) {

		
		connToDB();

		try {

			PREP_STMT = CONN.prepareStatement(deleteFromDB);
			PREP_STMT.setInt(1, animalId);

			PREP_STMT.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static String deleteFromDB = "DELETE FROM `assignments`.`zoo_animals` WHERE animal_id = ?";

	
	public static void updateDB() {

		System.out.println("Please enter the ID number of the animal you would like to update.");
		int updateAnimalId = Integer.parseInt(sc.nextLine());
		
		Animal animalToUpdate = new Animal();
		animalToUpdate = aboutTheAnimal();
		
		connToDB();

		try {

			PREP_STMT = CONN.prepareStatement(updateDB);
			
			PREP_STMT.setString(1, animalToUpdate.getAnimalType());
			PREP_STMT.setString(2, animalToUpdate.getName());
			PREP_STMT.setInt(3, animalToUpdate.getAge());
			PREP_STMT.setString(4, animalToUpdate.getDiet());
			PREP_STMT.setInt(5, updateAnimalId);

			PREP_STMT.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static String updateDB = ("UPDATE `assignments`.`zoo_animals` SET `animal_species` = ?,	`name` = ?, `age` = ?, `diet` = ? WHERE `animal_id` = ?");

	
} // Class
