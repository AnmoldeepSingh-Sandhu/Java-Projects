/*
    Purpose: convert given input to soundEx code 
    Input:   string, character(controlling the outermost if condition) 
    Output:  string
*/



import java.util.Scanner;

public class Soundex {


    public static Scanner keyboard = new Scanner(System.in);

    /**
      convert given string or names to soundEx codes

      @param firstName the first name given by user.
      @param secondName the first name given by user.
     */

    private static void soundEx(String firstName, String secondName){

        String first = firstName.toUpperCase(); //convert firstName to uppercase
        String second = secondName.toUpperCase(); //convert secondName to uppercase

        int index1 = 1; // trace the index of each character in first name
        int index2 = 1; // trace the index of each character in second name
        
        String newName1 = first.substring(0,1); //it will save the soundEx coding for first name 
        String newName2 = second.substring(0,1); //it will save the soundEx coding for second name

        char command = 'r'; //it will control the outer while loop

        boolean repeat = true;// you to print thanks statement

        if(command != 's'){

            
            //First Name
            // This will change the first name to soundex coding
            while(index1 < first.length()){

                

                if(first.charAt(index1) == 'A' || first.charAt(index1) == 'E' || first.charAt(index1) == 'H' || first.charAt(index1) == 'I' || first.charAt(index1) == 'O' || first.charAt(index1) == 'U' || first.charAt(index1) == 'Y' || first.charAt(index1) == 'W')
                {
    
                    newName1 += "0";
    
                }
    
    
                if(first.charAt(index1) == 'B' || first.charAt(index1) == 'P' || first.charAt(index1) == 'F' || first.charAt(index1) == 'V'){
    
                    newName1 += "1";
    
                }
    
    
                if(first.charAt(index1) == 'C' || first.charAt(index1) == 'S' || first.charAt(index1) == 'K' || first.charAt(index1) == 'G' || first.charAt(index1) == 'J' || first.charAt(index1) == 'Q' || first.charAt(index1) == 'X' || first.charAt(index1) == 'Z')
                {
    
                    newName1 += "2";
    
                }
    
                if(first.charAt(index1) == 'D' || first.charAt(index1) == 'T'){
    
                    newName1 += "3";
    
                }
    
                if(first.charAt(index1) == 'L'){
    
                    newName1 += "4";
    
                }
    
                if(first.charAt(index1) == 'M' || first.charAt(index1) == 'N'){
    
                    newName1 += "5";
    
                }
    
                if(first.charAt(index1) == 'R'){
    
                    newName1 += "6";
    
                }
    
                index1++;
                
    
            }

            
            
            deleteDublicate1(newName1);


            


            //Second Name
            // This will change the second name to soundex coding

            while(index2 < second.length()){

                

                if(second.charAt(index2) == 'A' || second.charAt(index2) == 'E' || second.charAt(index2) == 'H' || second.charAt(index2) == 'I' || second.charAt(index2) == 'O' || second.charAt(index2) == 'U' || second.charAt(index2) == 'Y' || second.charAt(index2) == 'W')
                {

                    newName2 += "0";

                }


                if(second.charAt(index2) == 'B' || second.charAt(index2) == 'P' || second.charAt(index2) == 'F' || second.charAt(index2) == 'V'){

                    newName2 += "1";

                }


                if(second.charAt(index2) == 'C' || second.charAt(index2) == 'S' || second.charAt(index2) == 'K' || second.charAt(index2) == 'G' || second.charAt(index2) == 'J' || second.charAt(index2) == 'Q' || second.charAt(index2) == 'X' || second.charAt(index2) == 'Z')
                {

                    newName2 += "2";

                }

                if(second.charAt(index2) == 'D' || second.charAt(index2) == 'T'){

                    newName2 += "3";

                }

                if(second.charAt(index2) == 'L'){

                    newName2 += "4";

                }

                if(second.charAt(index2) == 'M' || second.charAt(index2) == 'N'){

                    newName2 += "5";

                }

                if(second.charAt(index2) == 'R'){

                    newName2 += "6";

                }

                index2++;
                

            }

            


            deleteDublicate2(newName2);
            




            compareCodes(deleteDublicate1(newName1),deleteDublicate2(newName2));



            // This will ask the user if they want to play again or not
            System.out.print("\nPlease enter S to exit: ");
            
            char input = keyboard.next().toLowerCase().charAt(0);

            command = input;



            if(command != 's'){

                repeat();

            }else{

                repeat = false;
            }
                
             

        }


        if(repeat == false){

            System.out.println("Thanks for playing.");
        }



    }




    /**
     This method will delete all zeros and any other dublicate digit from the soundEx code of first name.

      @param newName1 is the string which will save soundEx coding for first name
      @return newName1.So, it can be used by other methods.
     */


    // This method will delete the dublicate numbers and zeros from the first name
    private static String deleteDublicate1(String newName1){


        int count1 = newName1.length()-1;

        while(count1 > 0){

            int index1 = newName1.length()-1;

            while(index1 > 0){

                if(newName1.charAt(index1) == '0'){
                        
                    newName1 = newName1.substring(0, index1) + newName1.substring(index1 + 1);

                    count1--;    
                }

                index1--;

            }



            

            char specificValue = newName1.charAt(count1);

            int countOne = count1;

            while(countOne > 0){


                if(newName1.charAt(countOne - 1) == specificValue){

                    newName1 = newName1.substring(0, countOne) + newName1.substring(countOne + 1);

                    count1--;
                }

                countOne--;

            }


            count1--;
        }


        while(newName1.length() < 4){

            newName1 += "0";
        }


        return newName1;


    }//deleteDublicate1()



    /**
     This method will delete all zeros and any other dublicate digit from the soundEx code of second name.

      @param newName2 is the string which will save soundEx coding for second name
      @return newName2.So, it can be used by other methods.
     */



    // This method will delete the dublicate numbers and zeros from the second name
    private static String deleteDublicate2(String newName2){

        int count2 = newName2.length()-1;

        while(count2 > 0){

            int index2 = newName2.length()-1;

            while(index2 > 0){

                if(newName2.charAt(index2) == '0'){
                        
                    newName2 = newName2.substring(0, index2) + newName2.substring(index2 + 1);

                    count2--;
                        
                }

                index2--;

            }


            char specificValue = newName2.charAt(count2);

            int countTwo = count2;


            
            while(countTwo > 0){


                if(newName2.charAt(countTwo - 1) == specificValue){

                    newName2 = newName2.substring(0, countTwo) + newName2.substring(countTwo + 1);

                    count2--;

                }

                countTwo--;

            }

            count2--;
        }


        while(newName2.length() < 4){

               newName2 += "0";
        }

        return newName2;

    }//deleteDublicate2()


    /**
      This method will compare the if the both given names sound same or not

      @param newName1 is the soundEx code for first name
      @param newName2 is the soundEx code for second name
     */


    // This method will compare the both coded strings if they are sounds same or not
    private static void compareCodes(String newName1,String newName2){
        
        System.out.println("\nSoundex code for first name: " + newName1);

        System.out.println("\nSoundex code for second name: " + newName2);


        if(newName1.equals(newName2)){

            System.out.println("\nBoth names sounds the same.");

        }else{

            System.out.println("\nBoth names do not sounds the same.");
        }

    }//compareCodes()


    /**
        This method will ask the user for new inputs. If he/she wants to continue playing with it.
     */

    private static void repeat(){

        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nPlease enter a first name: ");

        String userInput1 = keyboard.nextLine();

        System.out.print("\nPlease enter a second name: ");

        String userInput2 = keyboard.nextLine();

        soundEx(userInput1, userInput2);

        keyboard.close();
        
    }


    
    //main
    public static void main(String[] args) {

        Scanner getInput = new Scanner(System.in);


        System.out.print("Please enter a first name: ");

        String userInput1 = getInput.nextLine();


        System.out.print("\nPlease enter a second name: ");

        String userInput2 = getInput.nextLine();


        soundEx(userInput1, userInput2);

        getInput.close();
        
    }//main


    
}
