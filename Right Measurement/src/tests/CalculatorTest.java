package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.Calculator;
import classes.Result;
import classes.Person;

import java.io.IOException;

public class CalculatorTest {

	private Calculator calculator = new Calculator();
	private Person person;
	private Result personResult;
	
	
	/*
	 * These methods are testing: 
	 * 1. Test option Insert person.
	 * 2. Test if Calculator is returning the appropriate results by calculating:
	 * 2.1. Test if 65kg and 160cm height results BMI equals 25.39;
	 * 2.2. Test if 65kg and 170cm height results BMI equals 22.49;
	 * 2.3. Test if 65kg and 180cm height results BMI equals 20.06;
	 * For Men, test:
	 * 1. 	If BMI bellow 20.7 is underweight;
	 * 1.1. If BMI between 20.7 and 26.4 is healthy weight;
	 * 1.2. If BMI above 26.4 is overweight;
	 * 2.	If ideal weight is equal 65kg case height is equal 170;
	 * 2.1. If ideal weight is equal 72kg case height is equal 180;
	 * 2.2. If ideal weight is equal 80kg case height is equal 190;
	 * 3. 	If body fat is 11% case BMI 19.0 and age is 20;
	 * 3.1.	If body fat is 13% case BMI 21.0 and age is 20;
	 * 3.2. If body fat is 20% case BMI 27.0 and age is 20;
	 * 3.3.	If body fat is 0% case BMI 10.0 and age is 18;
	 * For Women, test:
	 * 1.	If BMI bellow 19.1 is underweight;
	 * 1.1. If BMI between 19.1 and 25.8 is healthy weight;
	 * 1.2. If BMI above 25.8 is overweight;
	 * 2.   If ideal weight is equal 50 case height is equal 150;
	 * 2.1.	If ideal weight is equal 55 case height is equal 160;
	 * 2.2. If ideal weight is equal 60 case height is equal 170;
	 * 3.	If body fat is 22% case BMI 19.0 and age is 20;
	 * 3.1.	If body fat is 24% case BMI 21.0 and age is 20;
	 * 3.2.	If body fat is 31% case BMI 27.0 and age is 20;
	 * 3.3.	If body fat is 0% case BMI 3.0 and age is 7;
	 * 3. Test option Remove a person.
	 * 4. Test option Remove all.
	 * 5. Test file writing.
	 * 6. Test if can read previous wrote files.
	 */
	
	@Before
	public void testInsertPerson() {
		assertTrue(calculator.insertPerson("Person",20,'M',65,170));
		person = calculator.getPersonList().get(0);
		personResult = calculator.getResultList().get(0);
	}
	
	@Test
	public void testIf65kgAnd160cmEqual25BMI() {
		person.setHeight(160);
		assertEquals(25,(int)calculator.getBMI());
	}
	
	@Test
	public void testIf65kgAnd170cmEqual22BMI() {
		person.setHeight(170);
		assertEquals(22,(int)calculator.getBMI());
	}
	
	@Test
	public void testIf65kgAnd180cmEqual20BMI() {
		person.setWeight(65);
		person.setHeight(180);
		assertEquals(20,(int)calculator.getBMI());
	}
	
	@Test
	public void testIfMenBmiBellow20Dot7IsUnderweight() {
		personResult.setBmi(20.69);
		assertEquals("Underweight",calculator.getBMIFeedback());
	}
		
	@Test
	public void testIfMenBmiAbove20Dot7IsHealthyWeight() {
		personResult.setBmi(20.7); 
		assertEquals("Healthy weight",calculator.getBMIFeedback());
	}
	
	@Test
	public void testIfMenBmiAbove26Dot4IsOverweight() {
		personResult.setBmi(26.40000001);
		assertEquals("Overweight",calculator.getBMIFeedback());
	}
	
	@Test
	public void testIfMenIdealWeightIs65kgCaseHeightIs170() {
		assertEquals(65,(int)calculator.getIdealWeight());
	}
	
	@Test
	public void testIfMenIdealWeightIs72kgCaseHeightIs180() {
		person.setHeight(180);
		assertEquals(72,(int)calculator.getIdealWeight());
	}
	
	@Test
	public void testIfMenIdealWeightIs80kgCaseHeightIs190() {
		person.setHeight(190);
		assertEquals(80,(int)calculator.getIdealWeight());
	}
	
	@Test
	public void testIfMenBodyFatIs11CaseBmiIs19AndAgeIs20() {
		personResult.setBmi(19);
		person.setAge(20);
		assertEquals(11,calculator.getBodyFat());
	}
	
	@Test
	public void testIfMenBodyFatIs13CaseBmiIs21AndAgeIs20() {
		personResult.setBmi(21);
		person.setAge(20);
		assertEquals(13,calculator.getBodyFat());
	}
	
	@Test
	public void testIfMenBodyFatIs20CaseBmiIs27AndAgeIs20() {
		personResult.setBmi(27);
		person.setAge(20);
		assertEquals(20,calculator.getBodyFat());
	}
	
	@Test
	public void testIfMenBodyFatIs0CaseBmiIs10AndAgeIs18() {
		person.setAge(18);
		personResult.setBmi(10);
		assertEquals(0,calculator.getBodyFat());
	}
	
	@Test
	public void testIfWomenBmiBellow19Dot1IsUnderweight() {
		person.setGender('F');
		personResult.setBmi(19.099999);
		assertEquals("Underweight",calculator.getBMIFeedback());
	}
	
	@Test
	public void testIfWomenBmiAbove19Dot1IsHealthyWeight() {
		person.setGender('F');
		personResult.setBmi(19.1);
		assertEquals("Healthy weight",calculator.getBMIFeedback());
	}	
	
	@Test
	public void testIfWomenBmiAbove25Dot8IsOverweight() {
		person.setGender('F');
		personResult.setBmi(25.8000001);
		assertEquals("Overweight",calculator.getBMIFeedback());
	}
	
	@Test
	public void testIfWomenIdealWeightIs50kgCaseHeightIs150() {
		person.setGender('F');
		person.setHeight(150);
		assertEquals(50,(int)calculator.getIdealWeight());
	}
	
	@Test
	public void testIfWomenIdealWeightIs55kgCaseHeightIs160() {
		person.setGender('F');
		person.setHeight(160);
		assertEquals(55,(int)calculator.getIdealWeight());
	}
	
	@Test
	public void testIfWomenIdealWeightIs60kgCaseHeightIs170() {
		person.setGender('F');
		person.setHeight(170);
		assertEquals(60,(int)calculator.getIdealWeight());
	}
	
	@Test
	public void testIfWomenBodyFatIs22CaseBmiIs19AndAgeIs20() {
		person.setGender('F');
		personResult.setBmi(19);
		assertEquals(22,calculator.getBodyFat());
	}
	
	@Test
	public void testIfWomenBodyFatIs24CaseBmiIs21AndAgeIs20() {
		person.setAge(20);
		person.setGender('F');
		personResult.setBmi(21);
		assertEquals(24,calculator.getBodyFat());
	}
	
	@Test 
	public void testIfWomenBodyFatIs31CaseBmiIs27AndAgeIs20() {
		person.setGender('F');
		personResult.setBmi(27);
		assertEquals(31,calculator.getBodyFat());
	}
	
	@Test
	public void testIfWomenBodyFatIs0CaseBmiIs3AndAgeIs7() {
		person.setGender('F');
		person.setAge(7);
		personResult.setBmi(3);
		assertEquals(0,calculator.getBodyFat());
	}
	
	@Test
	public void testIfRemoveOptionIsWorkingProperly() {
		assertTrue(calculator.remove(1));
		assertTrue(calculator.peopleListIsEmpty());
		assertTrue(calculator.resulListIsEmpty());
	}
	
	@Test
	public void testIfRemoveAllOptionRemovesEveryoneFromTheList() {
		assertTrue(calculator.insertPerson("Nicole",19,'F',63,170));
		assertTrue(calculator.insertPerson("Gabriela",19,'F',60,169));
		assertTrue(calculator.insertPerson("Rafael",23,'M',105,190));
		assertTrue(calculator.cleanBothLists());
		assertTrue(calculator.peopleListIsEmpty());
		assertTrue(calculator.resulListIsEmpty());
	}
	
	@Test
	public void testIfCanSaveInMemory() throws IOException {
		assertTrue(calculator.save());
	}

	@Test
	public void testIfLoadMethodCanReadSavedFiles() throws IOException, ClassNotFoundException {
		assertTrue(calculator.save());
		assertTrue(calculator.cleanBothLists());
		assertTrue(calculator.load());
		assertEquals("Person",calculator.getPersonList().get(0).getName());
	}
}
