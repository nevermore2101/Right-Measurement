package main;

import java.io.IOException;

import ui.CalculatorConsoleUI;

public class Main {	
	private static CalculatorConsoleUI ui = new CalculatorConsoleUI();

	public static void main(String[] args) {				
		try {
			
			ui.reload();
		
		} catch (ClassNotFoundException | IOException e1) {
			
			System.out.println("No saved file found!");
			
		}
			
		start();		
	
	}
	
	private static void start() {
		try {
			
			ui.menu();	
			
		} catch (java.util.InputMismatchException i) {
				
			System.out.println("\nWrong input type!");
			start();
			
		}
	}
}