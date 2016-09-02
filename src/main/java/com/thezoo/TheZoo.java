package com.thezoo;

import java.util.Scanner;

public class TheZoo {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String userMenuInput = null;
		boolean menuCorrect = false;
		
		System.out.println("Welcome to the zoo animal information program");
		
		
		do {
			System.out.println("Press 1 to read the database." + "\nPress 2 to add to the database." 
					+ "\nPress 3 to delete from the database." + "\nPress 4 to update an animal in the database.");
			userMenuInput = sc.nextLine();
			
			switch (userMenuInput) {
			case "1":
				DAO.readFromDB();
				break;
			case "2":
				//Write to the database here
//				DAO.writeToDB();
				break;
			case "3":
				//Delete from database here
//				DAO.deleteFromDB();
				break;
			case "4":
				//Update record in the database
				DAO.updateDB();
				break;

			default:
				System.out.println("You've entered an invalid option");
				menuCorrect = true;
				break;
			}
		} while (menuCorrect);
		
		sc.close();
	}

}
