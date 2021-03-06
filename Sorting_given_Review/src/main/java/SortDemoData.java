package main.java;

// SortDemo.java     
import java.util.*;   // for class StringTokenizer


public class SortDemoData  {
               
        public Item[] myArray;
        public String algo;

        public SortDemoData(){
        	myArray = null;
        }
        
        
        // read numbers from input to array myArray
        public void initializeArray(String string) throws NumberFormatException, 
        NoSuchElementException, NegativeArraySizeException{

	        	StringTokenizer inputTokens = new StringTokenizer(string);
	        	String[] result = string.split("\\s");

                String tempStr = "";
                    
                int arrayLength; // will be the length of myArray        
                if (inputTokens.hasMoreTokens()) {
                        arrayLength = inputTokens.countTokens(); 
                } else {
                        throw new NoSuchElementException();
                }

                // define the array of the right length n    
                if (arrayLength == 1) { 

                        // interpret the next number as number of entries and 
                        // generate a random vector with entries 1 ... n
                        tempStr = inputTokens.nextToken(); 
                        arrayLength =  Integer.valueOf(tempStr).intValue(); 
                        // may throw NegativeArraySizeException
                        
                        if (arrayLength == 0 || arrayLength == 1) {
                        	arrayLength = 5;
                        }
                  
                        if (arrayLength < 0) {
                        	arrayLength = Math.abs(arrayLength);
                        }
                        
                        myArray = new Item[arrayLength];
                        boolean[] tempArray = new boolean[arrayLength + 1];
                        RandomNumber randGen = new RandomNumber();
                        int randNr;
                        for (int i = 0; i < arrayLength; i++) {
                                do 
                                        randNr = randGen.nextIntRand(1, arrayLength);
                                while (tempArray[randNr]);                  
                                tempArray[randNr] = true;
                                myArray[i] = new Item(randNr);
                        }
                } else {           
                        Item [] array = new Item[arrayLength];
                        int count =0;
                        // read the n numbers into the array
                        for (int i = 0; i < arrayLength; i++) {
                        	

                                tempStr = result[i];
                                if(Arrays.asList(Arrays.copyOfRange(result, 0, i)).contains(tempStr)){
                                	count = count + 1;
                                }
                                else{
                                	array[i-count] = new Item( 
                                	        Integer.valueOf(tempStr).intValue());
                                }

                                
                        }
                        myArray = new Item[arrayLength-count];
                        myArray = Arrays.copyOfRange(array, 0, arrayLength-count);
                        
                        
               } 
        }
             
        /**
    	 * All methods should have a Javadoc according to STYLE.
    	 * @param choice: chosen sort algorithm, needs to be a number between 0 and 5. If the number is out of range 0 should be chosen
    	 * The attribute algo should always contain the choosen algorithm as string
    	 * @throws Exception as per typical main specifications
    	 */
        public StringBuffer runAlgo(int choice) {     
                        Item[] copyOfMyArray = new Item[myArray.length];
                        System.arraycopy(
                                myArray, 0, copyOfMyArray, 0, myArray.length);
                        
                        //SER 316
                        //if (choice < 0 || choice > 5) choice = 0;
                        
                   
                        switch (choice) {
                        case 0 : { SortAlgos.bubbleSort(myArray);  
                        			algo = "Bubble Sort";
                                   break;
                                  }
                        case 1 : { SortAlgos.selectionSort(myArray); 
                        			algo = "Selection Sort";
                                   break;
                                 }
                        case 2 : { SortAlgos.insertionSort(myArray); 
                        			algo = "Insertion Sort";
                                   break;
                                 }
                        case 3 : { SortAlgos.mergeSort(myArray);
                        			algo = "Merge Sort";
                                   break;
                                 }
                        case 4 : { SortAlgos.quickSort(myArray); 
                        			algo = "Quick Sort";
                                   break;
                                 }
                        case 5 : { SortAlgos.heapSort(myArray);     
                        			algo = "Heap Sort";
                                   break;
                                 }
                        default: SortAlgos.bubbleSort(myArray);  
			            			algo = "Bubble Sort";
			                        break;
                        		} 
                        
                        // output initial sequence of unsorted and sorted array 
                        int limit = Math.min(myArray.length, 1024);
                        StringBuffer outputBuf = new StringBuffer();
                        outputBuf.append(algo + "\n");
                        for (int i = 0; i < limit; i++) {
                                outputBuf.append(
                                        i + ": " 
                                          + Integer.toString( 
                                                       copyOfMyArray[i].getKey())  
                                          + " --> "
                                          + Integer.toString(myArray[i].getKey()) 
                                          + "\n");
                        }
                        outputBuf.append("\r");
                        		
               return outputBuf;            
        }        		
}