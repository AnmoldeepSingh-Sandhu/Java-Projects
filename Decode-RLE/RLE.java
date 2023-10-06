public class RLE {
    
    public static String decodeRLE(String codedString, char delimiter){

        String decodedString = "";
        int howMany = 0; // it will track how many character need to add in the string
        int repeat = 0; // it will get the number for how many times character need to repeat
        char specificValue = 'a'; // it will get number which will going to repeat

        for(int i = 0; i < codedString.length(); i++){

            int value = Character.getNumericValue(codedString.charAt(i));


            if(codedString.charAt(i) == delimiter){

                if(i+2 < codedString.length()){

                    repeat = Character.getNumericValue(codedString.charAt(i + 1)); 


                    
                    if(repeat >= 0 && repeat <= 9){

                        specificValue = codedString.charAt(i + 2);

                        while(howMany < repeat){

                            decodedString += specificValue;

                            howMany++;

                        }

                        howMany = 0;
                        
                    }else{

                        repeat = Character.getNumericValue(codedString.charAt(i + 2));
                        specificValue = codedString.charAt(i + 1);
                        
                        while(howMany < repeat){

                            decodedString += specificValue;

                            howMany++;

                        }

                        howMany = 0;
                    }

                    i += 2;
                }
                
            }else if(value <= 9){

                if(i + 2 < codedString.length() && codedString.charAt(i+1) == delimiter){

                    repeat = Character.getNumericValue(codedString.charAt(i)); 

                    specificValue = codedString.charAt(i + 2); 

                    while(howMany < repeat){

                        decodedString += specificValue;

                        howMany++;

                    }

                    howMany = 0;

                    i += 2;

                }else{
                    decodedString += codedString.charAt(i);
                }

            }else if(codedString.charAt(i) != delimiter){

                decodedString += codedString.charAt(i);

            }
        }

        return decodedString;

    }
}
