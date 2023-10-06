/*
    Purpose: Draw a rectangle inside the poisite (x,y) quadrant
    Input: integer, character 
    Output:  rectangle shape inside the positive (x,y) quadrant
*/


import java.util.Scanner;


public class RectanglePlotter {

    /**
     * This method is going to check if coordinates given by the user are good or not
     * 
     * @param x is the x coordinate
     * @param y is the y coordinate
     * @param width is the width of the rectangle
     * @param height is the height of the rectangle
     */

    //drawRectangle
    private static void drawRectangle(int x,int y, int width, int height){


        if((x > 0 && x <= 40) && (y > 0 && y <= 20)){

            drawGrid(x, y, width, height);
            
            System.out.println("\n" + "\nX: " + x);
            System.out.println("Y: " + y);

        }else{

            System.out.println("\nError: Invalid input");

        }
        
        repeat();
        
    }//drawRectangle






    /**
     * This method is going to make the grid for the (x,y) positive quadrant
     * 
     * @param x is the x coordinate
     * @param y is the y coordinate
     * @param width is the width of the rectangle
     * @param height is the height of the rectangle
     */

    // drawGrid
    private static void drawGrid(int x, int y, int width, int height){

        int countY = 21;//trace the grid for y coordinate

        int countX = 40;//trace the grid for x coordinate

        while(countY > 0){

            if(countY == 21){
                ;

            }else if(countY == 20){

                System.out.println("\n   ^");
                System.out.println(countY + " +");

            }else if(countY == 15 || countY == 10){

                System.out.println(countY + " +");

            }else if(countY == 5){

                System.out.println(" " + countY + " +");

            }else{
                System.out.println("   |");
            }

            countY--;





            if(countY == y){

                drawShape(x,width, height, countY);

                countY -= height;

            }



        }


        while(countX >=0){

            if(countX == 0){
                System.out.println("+>");

            }else if(countX == 40){
                System.out.print(" " + 0 + " +");

            }else if(countX%5 == 0){
                System.out.print('+');

            }else{
                System.out.print('=');

            }

            countX--;

        }


        // this is just going to add the numbering for the x coordinate grid
        int count = 0;

        while(count < 41){

            if(count == 5){

                System.out.print("    " + count);

            }else if(count%5 == 0){
                System.out.print("   " + count);
            }

            count++;
        }

    }// drawGrid








    /**
     * This method going the make a rectangle inside the positive quadrant 
     * 
     * @param x is the x coordinate
     * @param y is the y coordinate
     * @param width is the width of the rectangle
     * @param countY is the value of y coordinate where the rectangle going to start built
     */

    // drawShape
    private static void drawShape(int x, int width, int height, int countY){

        int index = 0;
        int count = 0;
        

        while(index < width){

            if(index == 0){

                if(countY == 5){

                    System.out.print(" " + countY + " +");

                }else if(countY%5 == 0){

                    System.out.print(countY + " +");
                    
                }else{

                    System.out.print("   |");
                }

                countY--;


                int countingX = 1;

                while(countingX < x){

                    System.out.print(" ");
                    countingX++;
                }

            }

            System.out.print('*');

            index++;
            


            if(index == width){
                
                System.out.println();
                index = 0;
                count++;
            }


            if(count == height){

                index = width;
            }

        }

    }//drawShape





    /**
     * This will ask the user if he/she want to play again or not
     * 
     */

    //repeat
    private static void repeat(){

        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nDo you want to play again (y/n): ");

        char again = keyboard.next().toLowerCase().charAt(0);

        if(again == 'y'){

            System.out.print("\nEnter a value of x coordinate from 1 to 40: ");

            int xCoordinate = keyboard.nextInt();

            System.out.print("\nEnter a value of y coordinate from 1 to 20: ");

            int yCoordinate = keyboard.nextInt();


            System.out.println("\nPlease enter the width and height that will fits in the (40,20) positive quadrant.");

            System.out.print("\nPlease enter the width for the rectangle: ");

            int wide = keyboard.nextInt();
        
            System.out.print("\nPlease enter the height for the rectangle: ");

            int high = keyboard.nextInt();

            drawRectangle(xCoordinate, yCoordinate, wide, high);

        }else{

            System.out.println("\nThank You for playing.\n");

        }

        keyboard.close();


    }//repeat






    //main
    public static void main(String[] args) {

        Scanner getInput = new Scanner(System.in);

        System.out.print("Enter a value of x coordinate from 1 to 40: ");

        int xCoordinate = getInput.nextInt();

        System.out.print("\nEnter a value of y coordinate from 1 to 20: ");

        int yCoordinate = getInput.nextInt();


        System.out.println("\nPlease enter the width and height that will fits in the (40,20) positive quadrant.");

        System.out.print("\nPlease enter the width for the rectangle: ");

        int width = getInput.nextInt();
        
        System.out.print("\nPlease enter the height for the rectangle: ");

        int height = getInput.nextInt();


        drawRectangle(xCoordinate, yCoordinate, width, height);

        getInput.close();
        
    }//main
    

}
