package com.lynda.javatraining.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputHelper {
	
	public static double getDoubleInput(String prompt) {
		String input = getInput(prompt);
		return Double.parseDouble(input);
	}

	private static String getInput(String prompt) {
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
	
	public static int getIntegerInput(String prompt) throws NumberFormatException {
		String input = getInput(prompt);
		return Integer.parseInt(input);	
	}

}
