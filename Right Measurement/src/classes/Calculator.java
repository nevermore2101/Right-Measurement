package classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Calculator {
	private ArrayList<Person> peopleList;
	private ArrayList<Result> resultList;
	private FileOutputStream writePersonData, writeResultData;
	private ObjectOutputStream writePersonStream, writeResultStream;
	private FileInputStream readPersonData, readResultData;
	private ObjectInputStream readPersonStream, readResultStream;
	
	public Calculator() {
		peopleList = new ArrayList<Person>();
		resultList = new ArrayList<Result>();
	}

	
	/*
	 * The insertPerson() method creates a Result and Person object and add it into the ArrayList of Result and the
	 * ArrayList of Person respectively and return TRUE if both objects were added into their lists.
	 */
	public boolean insertPerson(String name, int age, char gender, double weight, double height) {		
		if(resultList.add(new Result(name,age,gender,weight,height,0,0,0)) && peopleList.add(new Person(name,age,gender,weight,height))){
			return true;
		}		
		return false;	
	}
	
	
	/*
	 * The getBMI() method returns the Body Mass Index calculation according to the getPersonWeight(), which 
	 * returns the weight of the last Person added in the list, and the getPersonHeight(), which returns the height
	 * of the last Person added in the list. After calculating the BMI, we update the Person's bmi value.
	 * EXPLAINING THE 10000*W
	 * Here the weight is multiplied by 10000 because I'm considering the height in cm. 
	 * As to convert meters to centimeters I have to multiply the height by 100, considering I have height^2, it's 100*100 = 10000
	 * number of times I have to increase the weight value to maintain the proportion.
	 */
	public double getBMI() {
		double bmi = (10000*getPersonWeight()) / (getPersonHeight() * getPersonHeight());
		getPerson().setBmi(bmi);
		return bmi;
	}
	
	/*
	 * The getBMIFeedback() method returns a feedback (underweight, healthy weight or overweight) according the the getBMI() result
	 * and the person's gender.
	 */
	public String getBMIFeedback() {
		//double bmi = getBMI();
		double bmi = getPersonBMI();
		String feedback = "";
		if(String.valueOf(getPersonGender()).equalsIgnoreCase("F")) {
			if(bmi<19.1) {
				feedback = "Underweight";
			}else if(19.1<=bmi && bmi<=25.8) {
				feedback = "Healthy weight";
			}else{
				feedback = "Overweight";
			}
		}
		if(String.valueOf(getPersonGender()).equalsIgnoreCase("M")) {
			if(bmi<20.7) {
				feedback = "Underweight";
			}else if(20.7<=bmi && bmi<=26.4) {
				feedback = "Healthy weight";
			}else{
				feedback = "Overweight";
			}
		}
		return feedback;
	}
	
	/*
	 * The getIdealWeight() method calculates and returns the ideal weight according to the Lorentz formula:
	 * W = (h - 100) - h - 150
	 * 				   -------		considering K = 4 for men and K = 2 for women
	 * 					  K
	 * After getting the idealWeight value, the idealWeight of Person is updated to this idealWeight value.
	 */
	public double getIdealWeight() {
		int k = 4;
		if(String.valueOf(getPersonGender()).equalsIgnoreCase("F")) {
			k = 2;
		}
		double idealWeight = (getPersonHeight() - 100) - ((getPersonHeight() - 150)/k); 
		getPerson().setIdealWeight(idealWeight);
		return idealWeight;
	}

	/* The getBodyFat() metheod calculates the body fat rate, set the result the the person's
	 * results list and return the body fat rate result.
	 */
	public int getBodyFat() {
		int s = 0;
		if(String.valueOf(getPersonGender()).equalsIgnoreCase("M")) {
			s = 1;
		}
		double bodyFat = (1.2*getPersonBMI()) - (10.8*s) + (0.23*getPersonAge()) - 5.4;
		if(bodyFat < 0) {
			bodyFat = 0;
		}
		getPerson().setBodyFat((int)bodyFat);
		return (int)bodyFat;
	}
	
	/*
	 * The remove() method receives an index and removes the Person and the Result in both Array lists in that index  position 
	 * and returns TRUE if the index received is valid and the objects have been removed from the lists. Otherwise this method
	 * throws an InputMismatchException.
	 */
	public boolean remove(int code) {
		int index = code-1;
		if(index >= 0 && index <=peopleList.size()) {
			peopleList.remove(index);
			resultList.remove(index);
			return true;			
		}
		throw new java.util.InputMismatchException();
	}
	
	/*
	 * The cleanBothLists() method executes the clean method in both Array lists and returns TRUE is both lists are
	 * empty.
	 */
	public boolean cleanBothLists() {
		peopleList.clear();
		resultList.clear();
		if(peopleList.isEmpty() && resultList.isEmpty())
			return true;
		return false;
	}
	
	/*
	 * The save() method creates a file named PeopleRegistered.ser where the ArrayList of Person is copied 
	 * and a ResultsCalculated.ser were the ArrayList of Result is copied and returns true is the process was
	 * done successfully and an IOException was not thrown.
	 */
	public boolean save() throws IOException {
		writePersonData = new FileOutputStream("PeopleRegistered.ser");
		writePersonStream = new ObjectOutputStream(writePersonData);
		
		writePersonStream.writeObject(peopleList);
	    writePersonStream.flush();
	    writePersonStream.close();
	    
		writeResultData = new FileOutputStream("ResultsCalculated.ser");
		writeResultStream = new ObjectOutputStream(writeResultData);
	    
		writeResultStream.writeObject(resultList);
	    writeResultStream.flush();
	    writeResultStream.close();
	    
		return true;
	}
	
	/*
	 * The load() method reads the files created from the save() method in the last execution of this program. If no
	 * such file is found, a ClassNotFoundException is thrown to the main() method and a message is print in the console.
	 * If the files exist, an ArrayList of Person and an ArrayList of Result is created the is values are copied back to
	 * the Array lists and returns true if no exception was thrown.
	 */
	@SuppressWarnings("unchecked")
	public boolean load() throws IOException, ClassNotFoundException {
		readPersonData = new FileInputStream("PeopleRegistered.ser");
		readPersonStream = new ObjectInputStream(readPersonData);
		readResultData = new FileInputStream("ResultsCalculated.ser");
		readResultStream = new ObjectInputStream(readResultData);
		
		ArrayList<Person> personListSave = (ArrayList<Person>) readPersonStream.readObject();
		setPersonList(personListSave);
		readPersonStream.close();
		ArrayList<Result> resultListSave = (ArrayList<Result>) readResultStream.readObject();
		setResultList(resultListSave);
		readResultStream.close();
		return true;
	}
	
	//getters, setters and lists methods
	public ArrayList<Person> getPersonList() {
		return peopleList;
	}

	public void setPersonList(ArrayList<Person> personList) {
		this.peopleList = personList;
	}
	
	public ArrayList<Result> getResultList() {
		return resultList;
	}


	public void setResultList(ArrayList<Result> resultList) {
		this.resultList = resultList;
	}
	
	public boolean peopleListIsEmpty() {
		return peopleList.isEmpty();
	}
	
	public boolean resulListIsEmpty() {
		return resultList.isEmpty();
	}
	
	public int peopleListSize() {
		return peopleList.size();
	}
	

	//Get the actual person's values
	//The getPerson() method returns the last Person added in the list.
	private Result getPerson() {
		return resultList.get(resultList.size()-1);
	}
	
	//The getPersonHeight() method returns the height value of the last Person added in the list.
	private double getPersonHeight() {
		return peopleList.get(peopleList.size()-1).getHeight();
	}
	
	//The getPersonWeight() method returns the weight value of the last Person added in the list.
	private double getPersonWeight() {
		return peopleList.get(peopleList.size()-1).getWeight();
	}
	
	//The getPersonGender() method returns the gender Character (M or F) of the last Person added in the list.
	private char getPersonGender() {
		return peopleList.get(peopleList.size()-1).getGender();
	}
	
	//The getPersonAge() method returns the age of the last Person added in the list.
	private int getPersonAge() {
		return peopleList.get(peopleList.size()-1).getAge();
	}
	
	//The getPersonBMI() method returns the bmi of the last Person added in the list.
	private double getPersonBMI() {
		return resultList.get(resultList.size()-1).getBmi();
	}
}
