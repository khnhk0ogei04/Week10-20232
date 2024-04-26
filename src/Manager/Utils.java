package Manager;

import java.util.ArrayList;
import java.util.Scanner;

import Entity.Fruit;
import Entity.Order;

public class Utils {
	private static Scanner sc = new Scanner(System.in);
	public static int checkInputIntLimit(int min, int max) {
		while(true) {
			try {
				int result = Integer.parseInt(sc.nextLine().trim());
				if (result < min || result > max) {
					throw new NumberFormatException();
				}
				return result;
			} catch (NumberFormatException e) {
				System.out.println("Enter again: ");
			}
		}
	}
	public static String checkInputString() {
		while (true) {
			String result = sc.nextLine().trim();
			if (result.isEmpty()) {
				System.out.println("Enter again: ");
			} else {
				return result;
			}
		}
	}
	public static boolean checkInputString2(String input) {
		if (input.equals("")) return false;
		else return true;
	}
	public static Integer checkInputInteger() {
		while (true) {
			try {
				int result = Integer.parseInt(sc.nextLine().trim());
				return result;
			} catch (NumberFormatException e) {
				System.out.println("Nhập số nguyên nhé!");
			}
		}
	}
	public static Double checkInputDouble() {
		while (true) {
			try {
				Double result = Double.parseDouble(sc.nextLine().trim());
				return result;
			} catch (Exception e) {
				System.out.println();
			}
		}
	}
	public static boolean checkIdExist(ArrayList<Fruit> lf, String fruitId) {
		if (lf.size() == 0) return true;
		else {
			for (Fruit fruit: lf) {
			if (fruit.getFruitId().equalsIgnoreCase(fruitId)) {
				return false;
			}
		}
		return true;
		}
	}
	public static boolean checkItemExist(ArrayList<Order> lo, String fruitId) {
		for (Order order: lo) {
			if (order.getFruitId().equalsIgnoreCase(fruitId)) {
				return false;
			}
		}
		return true;
	}
	public static boolean checkInputYN() {
		System.out.println("Do you want to continue? (Y/N)");
		while (true) {
			String result = checkInputString();
			if (result.equalsIgnoreCase("Y")) {
				return true;
			}
			if (result.equalsIgnoreCase("N")) {
				return false;
			}
			else {
				System.out.println("Try again");
			}
		}
	}
	}
