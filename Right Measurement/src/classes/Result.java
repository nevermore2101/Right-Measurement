package classes;

public class Result extends Person {
	private static final long serialVersionUID = 1L;
	private double bmi;
	private double idealWeight;
	private double bodyFat;
	
	public Result(String name, int age, char gender, double weight, double height, double bmi,
			double idealWeight, double bodyFat) {
		super(name, age, gender, weight, height);
		this.bmi = bmi;
		this.idealWeight = idealWeight;
		this.bodyFat = bodyFat;
	}

	public double getBmi() {
		return bmi;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	public double getIdealWeight() {
		return idealWeight;
	}

	public void setIdealWeight(double idealWeight) {
		this.idealWeight = idealWeight;
	}

	public double getBodyFat() {
		return bodyFat;
	}

	public void setBodyFat(double bodyFat) {
		this.bodyFat = bodyFat;
	}
	
	public void showCalculationsResults() {
		if(!(getBmi() == 0)) {
			System.out.println("BMI: "+String.format("%.2f", getBmi()));
		}else {
			System.out.println("BMI: (Not calculated)");
		}
		if(!(getIdealWeight() == 0)) {
			System.out.println("Ideal Weight: "+String.format("%.1f", getIdealWeight())+"kg");
		}else {
			System.out.println("Ideal Weight: (Not calculated)");
		}
		if(!(getBodyFat() == 0)) {
			System.out.println("Body Fat: "+getBodyFat()+"%");
		}else {
			System.out.println("Body Fat: (Not calculated)");
		}
	}
}
