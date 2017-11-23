package com.lynda.javatraining.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputHelper {
	
	public static double getDoubleInput(String prompt) {
		String input = getInput(prompt);
		return Double.parseDouble(input);
	}

	public static String getInput(String prompt) {
		BufferedReader stdin = new BufferedReader(
				new InputStreamReader(System.in));
		
		System.out.println(prompt);
		System.out.flush();
		
		try {
			return stdin.readLine();
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}
	
	public static int getIntegerInput(String prompt) {//throws NumberFormatException {
		String input = getInput(prompt);
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.err.println("Number format is wrong " + e.getMessage());
			return 0;
		}	
	}

}
