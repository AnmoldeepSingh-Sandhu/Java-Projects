import java.io.PrintStream;
import java.util.Scanner;

public class TicTacToe{
	
	private static Scanner keyboard = new Scanner(System.in);

	public static final PrintStream S = System.out;

	public static char[][] board = new char[3][3];

	public static int countTurn = 0;

	
	/**
	This method will get the size of the board array and call the createBored method and repeat method.
	
	@parameter char[][] board array.  This array will specify the size of the board.
	*/
	private static void printBoard(char[][] board){
		

		createBoard(board);
		repeat();
		
	}
	
	
	/**
	This method is going to create the board of the game.
	This will also call checkWinner method.
	@parameter char[][] board array.  This array will specify the size of the board.
	*/
	private static void createBoard(char[][] board){

		S.println();
		
		for(int i = 0; i < board.length; i++){

			for(int j =0; j < board[i].length; j++){
				
				if(board[i][j] == 'X' || board[i][j] == 'O'){
					S.print(" " + board[i][j] + " ");
				}else{
					S.print("   ");
				}

				if(j < board.length-1){
					S.print("|");
				}
			}
			
			if(i < board.length-1){
				S.println("\n-----------");
			}
		}

	
		checkWinner(board);
		
	}
	
	/**
	This method is going to ask the user where he want to put the X and O on the game board.
	It will also call the validInput method and available method.
	*/
	private static void getInput(){

		S.println();
		S.println();

		String input = "";
		String value = "";

		if(countTurn % 2 == 0){
			S.print("Where you want to put the X (row,column): ");
			input = keyboard.next();
			value = input+"X";
			
			validInput(value);
			
			
		}else{
			System.out.print("Where you want to put the O (row,column): ");
			input = keyboard.next();
			value = input+"O";

			validInput(value);
			
		}

		countTurn++;

		available(value, board);

	}
	
	
	/**
	This method will ckeck if the user in put is valid or not.
	@parameter String input. input given by the user.
	*/
	private static void validInput(String input){

		if(Character.getNumericValue(input.charAt(0)) >= 0 && Character.getNumericValue(input.charAt(0)) <= 2){
			
		}else{
			S.println("Invaild input");
			getInput();
		}

		if(Character.getNumericValue(input.charAt(2)) >= 0 && Character.getNumericValue(input.charAt(2)) <= 2){
			
		}else{
			S.println("Invaild input");
			getInput();
		}
		
		
		
	}
	
	
	/**
	This method is going to check if location is avilable at the given input or not.
	*/
	
	private static void available(String input, char[][] board){
		
		int value1 = Character.getNumericValue(input.charAt(0));
		int value2 = Character.getNumericValue(input.charAt(2));


		if(board[value1][value2] == 'X' || board[value1][value2] == 'O'){
			S.println("This location already have the value in it. Type the right coordinates");
			countTurn--;
			getInput();
			
		}else{
			board[value1][value2] = input.charAt(3);
			createBoard(board);
		}
	}
	
	
	
	/**
	This method will ask the user if he want to play the game again.
	*/
	private static void repeat(){

		S.print("\nDo you want to play again (y/n) = ");

		String input = keyboard.next();

		if(input.toLowerCase().charAt(0) == 'y'){
			board = new char[3][3];
			printBoard(board);

		}else{
			S.println("\nThank You for playing\n");
		}
	}
	
	/**
	This method will check who is the winner 
	*/
	private static void checkWinner(char[][] board){

		if((board[0][0] == 'X' || board[0][0] == 'O') && board[0][0] == board[0][1] && board[0][0] == board[0][2]){
			S.println("\n\nPlayer with value "+ board[0][0] +" is the winner.");

		}else if((board[1][0] == 'X' || board[1][0] == 'O') && board[1][0] == board[1][1] && board[1][0] == board[1][2]){
			S.println("\n\nPlayer with value "+ board[1][0] +" is the winner.");

		}else if((board[2][0] == 'X' || board[2][0] == 'O') && board[2][0] == board[2][1] && board[2][0] == board[2][2]){
			S.println("\n\nPlayer with value "+ board[2][0] +" is the winner.");

		}else if((board[0][0] == 'X' || board[0][0] == 'O') && board[0][0] == board[1][0] && board[0][0] == board[2][0]){
			S.println("\n\nPlayer with value "+ board[0][0] +" is the winner.");
			
		}else if((board[0][1] == 'X' || board[0][1] == 'O') && board[0][1] == board[1][1] && board[0][1] == board[2][1]){
			S.println("\n\nPlayer with value "+ board[0][1] +" is the winner.");
			
		}else if((board[0][2] == 'X' || board[0][2] == 'O') && board[0][2] == board[1][2] && board[0][2] == board[2][2]){
			S.println("\n\nPlayer with value "+ board[0][2] +" is the winner.");
			
		}else if((board[0][0] == 'X' || board[0][0] == 'O') && board[0][0] == board[1][1] && board[0][0] == board[2][2]){
			S.println("\n\nPlayer with value "+ board[0][0] +" is the winner.");
			
		}else if((board[0][2] == 'X' || board[0][2] == 'O') && board[0][2] == board[1][1] && board[0][2] == board[2][0]){
			S.println("\n\nPlayer with value "+ board[0][2] +" is the winner.");
			
		}else{

			int count = 0;

			for(int i = 0; i < board.length; i++){
				for(int j = 0; j < board.length; j++){
					if(board[i][j] == 'X' || board[i][j] == 'O'){
						count += 1;
					}
				}
			}//cheack if board is full

			if(count < 9){
				getInput();
			}else{
				S.println("\n\nThis match is draw.");
			}
		}

	}
	
	
	
	public static void main(String[] args) {
		
		printBoard(board);
        
	}
}

	
	
	
