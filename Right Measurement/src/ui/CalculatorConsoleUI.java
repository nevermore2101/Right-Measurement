package ui;

import java.io.IOException;
import java.util.Scanner;

import classes.Calculator;
import classes.Result;
import main.UI;

public class CalculatorConsoleUI implements UI {
	private static final byte OPTION_INSERT_PERSON = 1;
	private static final byte OPTION_CALCULATE_BMI = 2;
	private static final byte OPTION_CALCULATE_IDEAL_WEIGHT = 3;
	private static final byte OPTION_CALCULATE_BODY_FAT = 4;
	private static final byte OPTION_LIST_RESULTS = 5;
	private static final byte OPTION_EXIT = 6;
	private Scanner input;
	private Calculator calculator;
	
	public CalculatorConsoleUI() {
		calculator = new Calculator();
	}
	
	
	@Override
	public void menu() {
		do {
			showOptions();
		}while(options() != 6);

	}
	

	private void showOptions(){
		System.out.println("\n\tRight Measurement");
		System.out.print("\n"
				+ "[1] Register\n"
				+ "[2] Calculate BMI(IMC)\n"
				+ "[3] Calculate Ideal Weight\n"
				+ "[4] Calculate Body Fat\n"
				+ "[5] List of all results\n"
				+ "[6] Save and exit\n\nOption: ");
	}
	
	/*
	 * The options() method takes a integer input value and switches through the options. If the input value is an
	 * invalid option, a InputMismatchException is thrown. If the both lists are empty and any option except 1 and 6
	 * is selected, an IndexOutOfBoundsException is thrown, since the Calculator class method cannot find a person in
	 * the ArrayList to get the values needed to calculate. If a file cannot be created in save and quit option, an
	 * IOException is thrown.
	 */
	private byte options() throws java.util.InputMismatchException{
		byte option = getOption();
		try {
			switch(option) {
				case OPTION_INSERT_PERSON: insertPerson();		
					break;
				case OPTION_CALCULATE_BMI: calculateBMI();
					break;
				case OPTION_CALCULATE_IDEAL_WEIGHT: calculateIdealWeight();
					break;
				case OPTION_CALCULATE_BODY_FAT:	calculateBodyFat();
					break;
				case OPTION_LIST_RESULTS: listResults(); 				
					break;
				case OPTION_EXIT: saveAndQuit();
					break;
			}
		}catch(java.util.InputMismatchException err){
			System.out.println("\nInvalid input type!");
		}catch(IndexOutOfBoundsException iofb) {
			System.out.println("\nYou need to register first!");
		}catch(IOException e) {
			System.out.println("\nError while trying to save the file!");
		}
		
		return option;
	
	}
	
	/*The insertPerson() method will send name, age, gender, weight and height data to the insertPerson() method
	 * of the Calculator class which creates a Person object and add it in an ArrayList of Person if the age
	 * is within the range 2 to 100 inclusive.
	 */ 
	private void insertPerson() throws java.util.InputMismatchException{
		System.out.println("\n\tRegister");
		String name = getStringValue("Name: ");
		int age = getIntValue("Age: ");
		
		if(age >= 2 && age <= 100) {
			char gender = getCharValue("Gender (F/M): ");
			
			if(String.valueOf(gender).equalsIgnoreCase("F") || String.valueOf(gender).equalsIgnoreCase("M")) {
				double weight = getDoubleValue("Weight (kg): ");
				double height = getDoubleValue("Height (cm): ");
				
				if(calculator.insertPerson(name,age,gender,weight,height)) {
					System.out.println("\nRegistered!");
				}
			}else {
				System.out.println("Please inform M or F in gender section");
			}
		}else {
			System.out.println("Invalid age! \nAge must be higher then 2 and lower then 100");
		}
	}
	
	/*The calculateBMI() method gets the result of the getBMI() and getBMIFeedback() methods from the Calculator class
	 *  and print it on the console with a message to the user.
	 */
	private void calculateBMI() {
		double bmi = calculator.getBMI();
		String feedback = calculator.getBMIFeedback();
		System.out.println("Your BMI: "+String.format("%.2f", bmi)+"\t("+feedback+")");
	}
	
	/* The calculateIdealWeight() method gets the result of the getIdealWeight() method from the Calculator class
	 *  and print it on the console with a massage to the user.
	 */
	private void calculateIdealWeight() {
		double idealWeight = calculator.getIdealWeight();
		System.out.println("Ideal body weight: "+String.format("%.1f", idealWeight)+"kg");
	}
	
	/* The calculateBodyFat() method gets the result of the getBodyFat() method from the Calculator class and
	 * print it on the console with a massage to the user.
	 */
	private void calculateBodyFat() {
		double bodyFat = calculator.getBodyFat();
		System.out.println("Body Fat Percentage: "+bodyFat+"%");
	}
	
	/*
	 * The listResults() method performs a for-each loop, iterating through each Person object in the ArrayList of Person 
	 * (created in the Calculator class) and calling its toString() method, which returns the Name, age, gender, weight and 
	 * height. Then it calls the showInformation() method from the Result subclass which prints the BMI, Ideal Weight and
	 * Body Fat calculations (any already calculated).
	 */
	private void listResults(){
		if(calculator.peopleListIsEmpty()) {
			throw new IndexOutOfBoundsException();
		}else {
			int index = 1;
			System.out.println("\nList of all results\n");
			for(Result r: calculator.getResultList()) {
				System.out.println("Code: "+ index++ +"\n"+r.toString());
				r.showCalculationsResults();
				System.out.println();
			}
		}
		resultListOptions();	
	}
	
	/* The resultListOption() method is the menu for the listResult() method. The menu has two options that allows
	 * the user to remove a single person register from the list or remove all the registers from the list. 
	 * DELETE A PERSON:
	 * To delete a person, the user must inform that person`s code, which is that person`s index in the ArrayList. 
	 * If the code or index doesn`t exist the program returns to the main menu. If the code is valid, the remove() method
	 * from the Calculator class removes the Person object from the ArrayList of Person and that person`s results 
	 * (BMI, Ideal Weight and Body Fat calculations) from the ArrayList of Result.
	 * DELETE ALL REGISTER:
	 * The `Clear` option calls the cleanBothLists() method from the Calculator class, which calls the clear() method
	 * from both lists and returns true is both lists are empty.
	 */
	private void resultListOptions() {
		System.out.println("\n[1] Remove person\t[2] Clean\nPress any other number to continue");
		byte option = getOption();
		if(option == 1) {
			if(calculator.remove(selectCode("\nCode: "))) {
				System.out.println("\nSuccessfully removed!");
			}
		}else if(option == 2) {
			if(calculator.cleanBothLists()) {
				System.out.println("\nAll registers and results have been removed!");
			}
		}
	}
	
	/* The saveAndQuit() method calls the save() method from the Calculator class which creates two files:
	 * the PeopleRegistered.ser and ResultsCalculated.ser and writes the ArrayList of Person and the ArrayList
	 * of Results into these files respectively.
	 */
	private void saveAndQuit() throws IOException {
		if(calculator.save()) {
			System.out.println("\nSaved!");
			System.exit(0);
		}
	}
	
	/*The reload() method calls the reload() method from Calculator class which reads the PeopleRegistered.ser and 
	 * ResultsCalculated.ser files if it exists in the /src folder.
	 */
	public void reload() throws ClassNotFoundException, IOException {
		calculator.load();
	}
	
	//Methods to get inputs.
	private int getIntValue(String message) {
		System.out.print(message);
		return input.nextInt();
	}

	private double getDoubleValue(String message) {
		System.out.print(message);
		return input.nextDouble();
	}

	private String getStringValue(String message) {
		System.out.print(message);
		return input.next();
	}
	
	private char getCharValue(String message) {
		System.out.print(message);
		return input.next().charAt(0);
	}

	private byte selectCode(String message) {
		System.out.println(message);
		return input.nextByte();
	}
	
	private byte getOption() {
		input = new Scanner(System.in);
		return input.nextByte();
	}	
}
