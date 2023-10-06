/*
    Purpose: do the linear search, selection sort and binary search
    Input: integer, double, array 
    Output:  Original array generated, results of linear search, selection sort and binary search
*/


import java.util.Scanner;

public class SearchingAndSorting {


    private static int NumberOfComparisons = 0;


    //randomDoubleArray
    private static double[] randomDoubleArray(double[] doubleArray){


        int count = 0;//it will go through the entire array

        while(count < doubleArray.length){

        int low = 1;// lowest random value
        int high = 100;// highest randow value exclude 100
        int result = (int)(Math.random()*(high-low)) + low;


        doubleArray[count] = result;

        count++;
        }

        printDoubleArray(doubleArray);

        return doubleArray;

    }//randomDoubleArray 





    //printDoubleArray
    private static double[] printDoubleArray(double[] generatedArray){

        int count = 0;// it will count the number of digits

        System.out.print("\nOriginal Array: ");

        System.out.print("[");

        while(count < generatedArray.length){

            System.out.print(generatedArray[count]);

            if(count < generatedArray.length-1){
                System.out.print(", ");
            }

            count++; 
        }

        System.out.println("]");


        linearSearch(generatedArray, doubleNeeded(), 0, generatedArray.length -1);

        selectionSort(generatedArray, 0, generatedArray.length);

        return generatedArray;

    }//printDoubleArray






    //linearSearch
    private static int linearSearch (double[] list, double target, int start, int end){

        System.out.print("\nResult of Linear Search: ");

        while(start <= end){

            if(list[start] == target){

                System.out.print("\tIndex of a number " + target +" is " + start + "\n");

                NumberOfComparisons++;

                System.out.println("\nNumber of comparisons made for linear search: " + NumberOfComparisons);
        
                NumberOfComparisons = 0;
                
                return start;
            }

            NumberOfComparisons++;

            start++;
        }

        System.out.print("\tThe number " + target + " is not found in the array\n");

        System.out.println("\nNumber of comparisons made for linear search: " + NumberOfComparisons);
        
        NumberOfComparisons = 0;

        return -1;
    }//linearSearch






    //selectionSort
    private static void selectionSort(double[] list, int start, int end){

        System.out.print("\nArray after doing Selection Sort: ");

        System.out.print("[");
        
        
        for(int i = 0; i < end-1; i++){

            start = i;

            for(int j = i + 1; j < end; j++){

                if(list[j] < list[start]){
                    
                    start = j;
                }

            }

            double smallestNumber = list[start];   
            list[start] = list[i];  
            list[i] = smallestNumber;

            
            if(i == end-2){

                System.out.print(list[i] + ", ");
                System.out.print(list[end-1]);

            }else{
                System.out.print(list[i] + ", ");

            }

            NumberOfComparisons++;
        }

        System.out.print("]\n");

        System.out.println("\nNumber of comparisons made for selection sort: " + NumberOfComparisons);
        
        NumberOfComparisons = 0;

        binarySearch(list, doubleNeeded(), 0, list.length-1);

    }//selectionSort






    //binarySearch
    private static int binarySearch(double[] list, double target, int start, int end){

        System.out.print("\nResult of Binary Search: ");

        while(start <= end){

            int middle = start + (end - start)/2;

            if(list[middle] == target){

                System.out.print("\tIndex of a number " + target +" is " + middle + "\n");

                NumberOfComparisons++;

                System.out.println("\nNumber of comparisons made for binary search: " + NumberOfComparisons);
        
                NumberOfComparisons = 0;
                
                return middle;

            }else if(target < list[middle]){
                end = middle -1;

            }else{
                start = middle + 1;
            }

            NumberOfComparisons++;

        }


        System.out.print("\tThe number " + target + " is not found in the array\n");

        System.out.println("\nNumber of comparisons made for binary search: " + NumberOfComparisons);
        
        NumberOfComparisons = 0;
        
        return -start-1;
    }//binarySearch






    //doubleNeeded
    private static double doubleNeeded(){
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nEnter the value for which you want to find the index in the above array: ");

        double value = keyboard.nextInt();

        keyboard.close();

        return value;

    }//doubleNeeded





    //main
    public static void main(String[] args){

        Scanner getInput = new Scanner(System.in);

        System.out.print("Please enter the size of the array you want: ");

        int input = getInput.nextInt();

        double[] randomArray;

        randomArray = new double[input];

        randomDoubleArray(randomArray);

        getInput.close();

    }//main


}
