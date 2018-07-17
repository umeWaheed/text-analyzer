import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;


public class Test {
	static Scanner sc = new Scanner(System.in);
	
	public static char mainMenu() {
		System.out.println("Welcome to java text analyzer!");
		System.out.println("Please select an input option:");
		System.out.println("a. Keyboard");
		System.out.println("b. Text file");
		System.out.println("c. demo");
		System.out.println("d. exit");
		
		char input = sc.next().charAt(0);
		sc.nextLine();
		
		return input;
	}
	
	public static char selectPrint() {
		System.out.println("what would you like to print?");
		System.out.println("a. Print letter analysis");
		System.out.println("b. Print special character analysis");
		System.out.println("c. Print digits analysis");
		System.out.println("d. Print word frequency analysis");
		System.out.println("e. Print word length analysis");
		System.out.println("f. Return to main menu");
		char print = sc.next().charAt(0);
		sc.nextLine();
		
		return print;
	}
	
	public static void processFile(String path, Analyzer a) {
		try {
			//get file from file explorer
			File f = new File(path);
			
			if (f!=null) {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line = br.readLine();
				
				while(line!=null) {
					a.analyze(line);
					line = br.readLine();
				}
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args){
		char input = mainMenu();
		boolean flag = true;
		
		do {
			Analyzer ana = new Analyzer();
			flag = true;
			
		if (input == 'a') {
			System.out.println("Enter text:");
			String test = sc.nextLine();
			ana.analyze(test);
		}
		else if (input == 'b') {
			//opens file explorer for choosing file
			System.out.println("Enter path of the file");
			String path = sc.nextLine();
			processFile(path, ana);			
		}
		else if (input == 'c') {
			String demo = " i love programming & i love 12$, while i like to draw3";
			System.out.println("demo text is: "+demo);
			ana.analyze(demo);
		}
		else if (input == 'd') {
			break;
		}
		else {
			System.out.println("Enter valid input value\n");
			flag = false;
		}
		
		if (flag) {
			char print = selectPrint();

			do {
				if (print == 'f')
					break;
				else {
				ana.printFreq(print);
				print = selectPrint();
				}
			}while(print!='f');
		}
		
		input = mainMenu();
		}while(input!='d');
		System.out.println("Thankyou for using my program");
	}
}
