
import java.util.Scanner;


public class GuessTheNumber{

	private static Scanner keyboard = new Scanner(System.in);

	//This method will ask the user that how many digits long number he want to guess
	//howMany
	private static void howMany(){

		System.out.print("\nHow many digits you want to guess choose a number from 2 to 9: ");
		
		int userInput = keyboard.nextInt();
		
		while(userInput < 2 || userInput > 9){
			
			System.out.print("\nPlease, choose a number from 2 to 9: ");
		
			userInput = keyboard.nextInt();
			
		}
		
		generateArray(userInput);
	}//howMany


	
	
	
	/**
	@parameter value. It is the length of number user want to guess
	
	This method will create the array of the length of a number given by user
	and fill each cell of the array with randow unique number
	*/
	//generateArray
	private static void generateArray(int value){
        
		int count = 0;//this will help the fill each cell of array with random number

		int[] randomArray = new int[value];
		
		while(count < value){
			
			randomArray[count] = (int)(Math.random()*(10-1) + 1);
			
			
			for(int i = 0; i < count; i++){
				
				if(randomArray[i] == randomArray[count]){
					count--;
				}
			}
			
			count++;
		}
	
		System.out.print(randomArray[0]);
		System.out.print(randomArray[1]);
		// System.out.print(randomArray[2]);
		
		// int counting = 0;//limit the number of guesses
		guess(randomArray, value, count);
	}//generateArray

	
	
	
	
	
	/**
	@parameter randomArray. This is the random Array generated in previous method
	@parameter value. This the the value given by the user
	@parameter count.This will go through each digit (saved in the array and number guess by the user)
	
	This method will ask the user to guess the number
	*/
	//guess
	private static void guess(int[] randomArray, int value, int counting){
		
		
		System.out.print("\nGuess the " + value + " digit number: ");

		long givenValue = keyboard.nextLong();

		String input = 	String.valueOf(givenValue);
		
		
		if(counting > 10){
			
			System.out.println("\nSorry, you reach the limit of guessing a number.");
			
			String answer = "";
			for(int i = 0; i < value; i++){
				answer += randomArray[i];
			}
			
			System.out.println("Answer: " + answer);
			
			repeat();
			
		}else{
			checking(input, randomArray, value, counting);
		}

		counting++;
		
		
		
	}//guess
	
	
	
	
	
	
	
	/**
	@parameter input.This the number guess by the user that is converted into the String 
	@parameter randomArray. This is the random Array generated in previous method
	@parameter value. This the the value given by the user
	@parameter counting. This will trace how many times user guess the number
	
	This method will check if the number guess by the user and number saved in the array by computer are equal or not
	*/
	//checking
	private static void checking(String input, int[] randomArray, int value, int counting){
		
		int count = 0;//This will count the number of digits match(that exist in the array and number guessed by the user)
		int count2 = 0;//This will count the number of digits  that exist in their right place
	
		String convertedArray = "";//this will convert array value from integer to string
		
		boolean unique = true;//this will check if each digit is unique or not

		boolean guessed = false;
		
		
		for(int a = 0; a< value; a++){
			
			convertedArray += Integer.toString(randomArray[a]);
			
		}


		if(input.equals(convertedArray)){
				
			System.out.println("\nCongratulations! You guess the right number.");
			guessed = true;
			repeat();
			
		}


		if(guessed == false){

			convertedArray = "";
			
			for(int i =0; i < input.length(); i++){
		
				for(int j =0; j < randomArray.length; j++){
					
					convertedArray += Integer.toString(randomArray[j]);
				
					if(input.charAt(i) == convertedArray.charAt(j)){
						count++;	
					}
				
				}
				
				if(input.charAt(i) == convertedArray.charAt(i)){
					count2++;	
				}
				
				if(i < input.length() -1 && input.charAt(i) == input.charAt(i +1)){
					unique = false;
				}
				
			}
			
			
			
			if(unique == true){
				
				if(count == 0 || count == 1){
					System.out.println("\n" + count + " digit is correct and " + count2 + " digit is in their right place.");
					
				}else if(count2 < 2){
					System.out.println("\n" + count + " digits are correct and " + count2 + " digit is in their right place.");
					
				}else{
					System.out.println("\n" + count + " digits are correct and " + count2 + " digits are in their right place.");
				}
				
				guess(randomArray, value, counting);
				
			}else{
				
				
				System.out.println("\nYour every digit need to be unique. Try again.");
				
				guess(randomArray, value, counting);
			}
					
		}
		
		
	}//checking
	
	
	
	
	
	//This method will ask the user if he want to play again or not
	//repeat
	private static void repeat(){
		System.out.print("\nDo you what to play again (Y/N): ");
		char again = keyboard.next().toLowerCase().charAt(0);
		
		if(again == 'y'){
			howMany();
			
		}else{
			System.out.println("\nThank you for playing.");
			
			
		}
	}//repeat
	
	

	
	
	//main
	public static void main(String[] args) {
		
		howMany();
        
	}//main
	
}