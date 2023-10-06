/*
    Name: Anmoldeep Singh
    Student Number: 100374206

    Course: CPSC1150        Section 003
    Date: 03/11/2021
    Instructor: Bryan Green
    Purpose: decode the given rle string and then print them according to user
    Input:   string, character, integer
    Output:  string
*/



import java.util.Scanner;


public class DecodeRLE{


    //decodeRLE
    private static String decodeRLE(String rle, char delimiter){

        String decode = ""; //it will store the new decoded string

        int count = 0; //it will go through the each character of string rle 


        while(count < rle.length()){


            if(rle.charAt(count) != delimiter){

                decode += rle.charAt(count);
            }



            if(rle.charAt(count) == delimiter){


                int howMany = 0; // it will track how many character need to add in the string

                int repeat = Character.getNumericValue(rle.charAt(count + 1)); // it will get the number for how many times character need to repeat

                char specificValue = rle.charAt(count + 2); // it will get number which will going to repeat

                

                while(howMany < repeat){

                    decode += specificValue;

                    howMany++;

                }

                count+=2;    

            }

            count++;

        }

        return decode;
    }//decodeRLE








    //printPicture
    private static void printPicture(String decodedRLECode, int width){

        int count = 0;// it will count the characters printed in each row

        int index = 0;// it will go though each character of the string


        if(width > 0){

            while(count < width){

                System.out.print(decodedRLECode.charAt(index));

                if(count == width - 1){
                    System.out.println();
                    count = -1; 
                }


                if(index == decodedRLECode.length()-1){

                    count = width;

                }

                index++;

                count++;   

            }

        }else{

            System.out.println("Invalid input");

        }

    }//printPicture





    //main
    public static void main(String[] args) {

        Scanner getInput = new Scanner(System.in);


        //decodeRLE
        System.out.print("\nPlease enter a string with RLE encoding: ");

        String userInput1 = getInput.nextLine();

        System.out.print("\nPlease enter a delimiter character: ");

        char userInput2 = getInput.next().charAt(0);

        System.out.print("\nOriginal RLE: " + userInput1 + "\t");

        decodeRLE(userInput1, userInput2);

        System.out.println("\tDecorded RLE: " + decodeRLE(userInput1, userInput2));
        




        //printPicture
        System.out.print("\nPlease add the width you want: ");

        int width = getInput.nextInt();

        printPicture(decodeRLE(userInput1, userInput2), width);

        getInput.close();

    }//main

    
}
