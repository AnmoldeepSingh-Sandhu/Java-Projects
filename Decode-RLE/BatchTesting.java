/*
    Purpose: Take coded RLE and delimiter input from dataFile and return decoded RLE
    Input:  Character(ask the user he want to play again or not)
    Output:  Coded RLE, Delimiter, Expected Output, Actual Output, Number of Correct anwsers,  Message(if Expected and Actual outputs matches or not)
*/





import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BatchTesting {


	private static Scanner keyboard = new Scanner(System.in);

	//rleDriver
	private static void rleDriver() throws IOException{
	
		int count = 0;// number of correct answers
		int counting = 0;// total answers
		
		
		File data;
		data = new File("dataFile.txt");

		data = new File("C:\\Users\\anmol\\OneDrive\\Desktop\\Semester 2\\CPSC-1150\\Lab 10\\dataFile.txt");
		//C:\Users\anmol\OneDrive\Desktop\Semester 2\CPSC-1150\Lab 10

		Scanner input = new Scanner(data);

		char command = 'a'; //trace if user want to play again or not

		while(command != 's' && input.hasNext()){
	
			if(data.exists() && data.canRead()){

				String value = input.nextLine();

				while(value.charAt(0) == '/'){
					value = input.nextLine();
					
				}

				String codedString = value;
				char delimiter = input.nextLine().charAt(0);
				String output = input.nextLine();
				RLE.decodeRLE(codedString, delimiter);
				
				
				System.out.println("\nCoded String: " + codedString);
				System.out.println("Delimiter: " + delimiter);
				System.out.println("Expected Output: " + output);
				System.out.println("Actual Output: " + RLE.decodeRLE(codedString, delimiter));
				
				
				boolean equalOrNot = output.equals(RLE.decodeRLE(codedString, delimiter));
				
				if(equalOrNot == true){
					System.out.println("\nActual Output is equal to Expected Output");
					count++;
					counting++;
					
				}else{
					System.out.println("\nError: Actual Output does not equal to Expected Output");
					counting++;
				}
				
				
				System.out.println("\nYou got " + count +" correct anwser out of  "+ counting);
				
			}
			
			if(input.hasNext()){
				
				System.out.print("\nPlease enter S to stop: ");
				command = keyboard.next().toLowerCase().charAt(0);
				
			}else{
				System.out.println("\nThank you for playing you reach the end of the file.");
			}
			
			
			
			if(command == 's'){
				System.out.println("\nThank you for playing");
				
			}
		}

		input.close();

	}//rleDriver


	
	//main
	public static void main(String[] args) throws IOException{
		
		rleDriver();

	}//main

    
}
