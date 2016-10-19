/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: 0
 * Fall 2016
 */
package assignment4; // cannot be in default package
import java.util.Objects;
import java.util.Scanner;
import java.awt.List;
import java.io.*;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
        try {
        	int i = 0;
        	while(i < 100){ // 100
        		Critter.makeCritter("Algae");
        		i++;
        	}
        	i = 0;
        	while(i < 25){ //25
        		Critter.makeCritter("Craig");
        		i++;
        	}
		} catch (InvalidCritterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        kb = new Scanner(System.in);
        while(true){
        	System.out.print("critters> ");
			String str = kb.nextLine();
			
			String[] arr = str.trim().split("\\s+");
			
			// Need to throw a bunch of exceptions here....
			
			// Quit
			if (arr.length == 1 && arr[0].equals("quit")){
				System.out.flush();
				System.exit(0);
			}
			
			// Show
			if (arr.length == 1 && arr[0].equals("show")){
				Critter.displayWorld();
			}
			
			// Step
			if (arr.length >= 1 && arr.length <= 2 && arr[0].equals("step")){
				if (arr.length == 1){
					// step once
					Critter.worldTimeStep();
				}
				else{
					// step however many times by arr[1]
					int n = Integer.parseInt(arr[1]);
					for(int i = 0; i < n; i++){
						Critter.worldTimeStep();
					}
				}
			}
			
			// Seed
			if (arr.length == 2 && arr[0].equals("seed")){
				int n = Integer.parseInt(arr[1]);
				Critter.setSeed(n);
			}
			
			// Make
			if (arr.length >= 2 && arr.length <= 3 && arr[0].equals("make")){
				int count = 1;
				if (arr.length == 3){
					count = Integer.parseInt(arr[2]);
				}
				
				while(count > 0){
					try {
						Critter.makeCritter(arr[1]);
					} catch (InvalidCritterException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
					count--;
				}
			}
			
			if (arr.length == 2 && arr[0].equals("stats")){

			}
			
			if (!(arr[0].equals("quit") || 
				  arr[0].equals("show") || 
				  arr[0].equals("step") || 
				  arr[0].equals("seed") || 
				  arr[0].equals("make") || 
				  arr[0].equals("stats"))){
				
				System.out.printf("invalid command: ");
				
				for(int i = 0; i < arr.length; i++){
					System.out.printf(arr[i]);
					if (i != arr.length - 1){
						System.out.printf(" ");
					}
					
				}
				
				System.out.println("");
			}
			
		}
        
        
        /* Write your code above */
        // System.out.flush();

    }
}
