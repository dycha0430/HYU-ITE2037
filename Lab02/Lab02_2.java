package practice01;

import java.util.Scanner;

public class Lab02_2 {
	private static String makeOrdinalNumber(int num) {
		switch(num % 10) {
			case 1 :
				return num + "st";
			
			case 2 :
				return num + "nd";
				
			case 3 :
				return num + "rd";
		}
		return num + "th";
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		int score = 0;
		float sum = 0, average = 0;
		String[] score_arr = input.split(" ");
		
		for (int i = 0; i < score_arr.length; i++) {
			score_arr[i] = score_arr[i].toUpperCase();
			switch(score_arr[i]) {
				case "A" :
					score = 100;
					sum += 100;
					break;
				case "B" :
					score  = 90;
					sum += 90;
					break;
				case "C" :
					score = 80;
					sum += 80;
					break;
				case "D" :
					score = 70;
					sum += 70;
					break;
				case "F" :
					score = 0;
					break;
			}
			System.out.println(makeOrdinalNumber(i+1) + " student's score is " + score);
		}
		
		average = sum/score_arr.length;
		String fixedNum = String.format("%.2f",  average);
		System.out.println("This class's average = " + fixedNum);
	}

}
