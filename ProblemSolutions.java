/*
    Input:   Integers and Characters 
    Output:  Problem 1: print characters in specific shape(left triangle )
             Problem 2: find the pair of digits in given number
             Problem 3: find the given number is prefect number or not
             Problem 4: gives the fibonacci number and it ratio with previous number
*/

import java.util.Scanner;

public class ProblemSolutions{



    private static void printSymbol(int howMany, char whatSymbol){

        int count = 1; // count the rows
        int column = howMany;// it count column vise to put spaces and symbol 


        while(count <= howMany){

            column = howMany;


            while(column > count){

                System.out.print(" ");
                column--;

            }// it put spaces

            

            while(column > 0){

                System.out.print(whatSymbol);
                column--;
            }// it put symbol

            count++;

            System.out.println();
        }//printSymbol 

    }




    private static int determinePair(int number){


        boolean pair = false;// it will execute the last if statement in this method


        if(number > -10 && number < 10){

            System.out.println("Pair is not possible for this number");

            pair = true;

        }// for single digit number



        while(number <= -10 || number >= 10){

            int reminder = number % 10;

            int specificNumber = reminder;

            number = number/10;

            reminder = number % 10;


            if(reminder == specificNumber){
                System.out.println("Number " + specificNumber + " is in pair");

                pair = true;
            }

                  
        }// to check for pairs in a number



        if(pair == false){

            System.out.println("There is no pair");

        }// for no pair


        return -1;
    }// determinePair



    private static boolean prefectNumber(int value){

        int num = 1;//starting number
        int sum = 0;// sum of prefect divisors
        boolean output = true;// use for return only


        while(num <= value/2){

            if(value % num == 0){

                sum += num;

            }


            num++;

        }



        if(sum == value){

            System.out.println(true + ", it is a prefect number");

        }else{

            System.out.println(false + ", it is not prefect number");

        }


        return output;

    }// prefectNumber



    private static void fibonacciNumber(){

        int num0 = 0;
        int num1 = 1;

        double num0_ = num0;
        double num1_ = num1;
        int num2;// this will store fibonacci number in it 
        int start = 2;// this will start count numbers from 2 till 50
        int count  = 50;
        double ratio;// this will gives the ratio



        if(num0 == 0){

            System.out.print("\nNumber: " + num0 + "\t");
            System.out.println("Fibonacci Number: " + num0);

        }
        

        
        if(num1 == 1){

            System.out.print("Number: " + num1 + "\t");
            System.out.println("Fibonacci Number: " + num1);

        }



        while(start <= count){

            num2 = num0 + num1;

            System.out.print("Number: " + start + "\t");
            System.out.print("Fibonacci Number: " + num2 + "\t");

            num0 = num1;
            num1 = num2;

            num0_ = num0;
            num1_ = num1;

            ratio = num1_/num0_;

            System.out.print("Ratio: ");
            System.out.format("%.9f" , ratio);
            System.out.println();

            start++;

        }
    }//fibonacciNumber

    

    //main
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        // For problem 1
        System.out.print("Please enter a number: ");
        int howMany = keyboard.nextInt();//ask to give an integer


        System.out.print("\nPlease enter a character: ");
        char whatSymbol = keyboard.next().charAt(0);//ask to give a character

        printSymbol(howMany, whatSymbol);
        // end of problem 1




        //for problem 2
        System.out.print("\nPlease enter a number to check pair in it: ");
        int number = keyboard.nextInt();
        determinePair(number);
        // end of problem 2




        //for problem 3
        System.out.print("\nPlease enter a number to check for prefect number: ");
        int value = keyboard.nextInt();
        prefectNumber(value);


        //for problem 4
        fibonacciNumber();

        keyboard.close();
        
    }


  
}
