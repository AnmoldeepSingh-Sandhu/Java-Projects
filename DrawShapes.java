/*
    Purpose: dislay the shape or error message based on the value entered by the user 
    Input:   character only 
    Output:  draw shapes and show error message
*/



import java.util.Scanner;

public class DrawShapes{

    // This is the constant for the user's command to exit.
    final static char EXIT = 'E';
    final static char TRIANGLE = 'T';
    final static char SQUARE = 'S';
    final static char DIAMOND = 'D';

    public static void main (String[] args)
    {
        //testAllShapes();
        interactiveMenu();
    }
    
    // private static void testAllShapes()
    // {
    //     drawTriangle();
    //     printBlankLine();
    //     drawSquare();
    //     printBlankLine();
    //     drawDiamond();
    //     printBlankLine();
    //     drawLine(15);
    //     printBlankLine();
    //     drawSpaces(0);
    //     drawUpsideDownTriangle();
    //     System.out.println("Program Finished.  Thanks for playing.");
    // } // testAllShapes

    private static void drawSquare()
    {
        int count = 0;
        while(count < 7){
            drawLine(15);
            printBlankLine();
            count++;
        }
       
    } // drawSquare

    private static void drawTriangle()
    {
        drawSpaces(2);
        drawLine(1);
        printBlankLine();

        

        drawSpaces(1);
        drawLine(3);
        printBlankLine();
        
        

        drawLine(5);
        printBlankLine();
    } // drawTriangle




    private static void drawDiamond()
    {
        drawTriangle();
        drawUpsideDownTriangle();
    } // drawDiamond

    private static void drawLine(int length)
    {
        final char STAR = '*';
        int count = 1;
    
        while (count <= length)
        {
            System.out.print(STAR);
            count++;
        }
    } // drawLine



    private static void printBlankLine() {
        System.out.println();
    } // printBlankLine
    


    private static void drawSpaces(int length){

        int count = 1;
    
        while (count <= length)
        {
            System.out.print(" ");
            count++;
        }
        
    }//drawSpaces



    private static void drawUpsideDownTriangle()
    {

        drawSpaces(1);
        drawLine(3);
        printBlankLine();


        drawSpaces(2);
        drawLine(1);
        printBlankLine();

    } // drawUpsideDownTriangle



    // ----------------------------------------------------------------------
    
    private static void interactiveMenu(){

        Scanner keyboard = new Scanner(System.in);

        char command = 'A'; 

        while (command != EXIT){

            displayMenu(); // display the menu so the user knows what is available
            System.out.print("\nPlease type a Character: ");
            command = keyboard.next().toUpperCase().charAt(0); // get the single letter command


            while (!isValidInput(command)) // test command to see if it is an invalid letter
            {   
                // if so then display an error message
                System.out.print("\nError: Value does not exist in the given menu\n");


                displayMenu(); // followed by the menu again
                System.out.print("\nPlease type a Character: ");
                command = keyboard.next().toUpperCase().charAt(0); // ask again for the command

            }

            doCommands(command);
        }

        System.out.println("\nProgram Finished. Thanks for playing.");

        keyboard.close();
        
    }



    
    private static void displayMenu()
    {
        
        System.out.println("\nMenu");

        System.out.println( "(" + EXIT + ")xit -- Exit the program" );
        System.out.println( "(" + TRIANGLE + ")riangle -- draw a triangle" );
        System.out.println( "(" + SQUARE + ")quare -- draw a square" );
        System.out.println( "(" + DIAMOND + ")iamond -- draw a diamond" );

    } // displayMenu



    private static boolean isValidInput(char character)
    {
        return (character == EXIT || character == TRIANGLE || character == SQUARE || character == DIAMOND);

    } // isValidInput




    private static void doCommands(char command)
    {
        
        if (command == EXIT) 
        {
            ; // do nothing when you exit

        }else if(command == TRIANGLE){

            System.out.println();
            drawTriangle();

        }else if(command == SQUARE){

            System.out.println();
            drawSquare();

        }else if(command == DIAMOND){

            System.out.println();
            drawDiamond();

        }else{    
            // When all the modifications to this program are completed, this statement should never appear.
			System.out.println("doCommands method discovered an unknown command: '" + command + "'");
        }
    } // doCommands
    
}