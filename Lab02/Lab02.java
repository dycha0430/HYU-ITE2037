package practice01;

import java.util.Scanner;

public class Lab02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		String[] input_arr = input.split(",");
		String[] name = input_arr[0].split(" ");
		String[] homework = input_arr[1].split("\\.");
	
		System.out.println("Name Length(Korean) : "+ name.length);
		
		String newName = name[1].substring(0,1).toUpperCase() + "." + name[2].substring(0,1).toUpperCase() + "." + name[0].substring(0,1).toUpperCase() + name[0].substring(1) ;
		String newHomework = homework[0].substring(1,2).toUpperCase() + homework[0].substring(2);
		
		System.out.println(newName + " submitted " + newHomework + ".pdf");
	}
}
