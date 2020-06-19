package newzooauthenticationsystem;

import newzooauthenticationsystem.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewZooAuthenticationSystem {

    public static void main(String[] args) throws Exception 
    {
        // create variables needed for program
        int attempts = 0;
        String inputUsername = "";
        String inputPassword = ""; 
        NewAuthenticator authenticator = new NewAuthenticator();
        String userRole = "";
        String lineText = "";
        String userInput = "";
		String[] messagearray = new String [1];
        // while loop that iterates as long as less than 3 bad attempts are made
        while (attempts < 3) 
        {
            Scanner input = new Scanner(System.in);
            // display login screen and prompt user for input
            System.out.println("Accessing Zoo System");
            System.out.println("Welcome to the Zoo System!"); // ADDED WELCOME MESSAGE
            System.out.println("Please, enter username:"); // ADDED PLEASE
            inputUsername = input.nextLine();
            System.out.println("Please, enter password:"); // ADDED PLEASE
            inputPassword = input.nextLine();
            // call the "authenticate" method with parameters
            NewAuthenticator.authenticate(inputUsername, inputPassword);
            userRole = NewAuthenticator.getUserRole();
            
            // if statements to limit and count invalid logins
            if (userRole.equals("None"))
            {
                // limit number of attempts and increment them
                // exit system after 3 failed attempts
                attempts++;
                if (attempts == 3) {
                    System.out.println("Maximum number of attempts (3) reached. Exiting system.");
                    break;
                }
                else {
                    System.out.println("Username or password incorrect, try again.");                    
                }
            }
            else
            {
                attempts = 0;
                // read role info from file pertaining to login credentials
                Scanner scnr = new Scanner (new File(userRole + ".txt"));
                while (scnr.hasNextLine()) 
                {
                    lineText = scnr.nextLine();
                    System.out.println(lineText);
                }
                // give user the option to exit the program OR TO LEAVE/VIEW A MESSAGE
                System.out.println("\nEnter 'M' to leave a message");
                System.out.println("\nEnter 'V' to leave a message");
                System.out.println("\nEnter 'E' to Exit");
                userInput = input.nextLine();

                // OPTION TO VIEW MESSAGES
                if (userInput.equals("V")) {
                	// INITIALIZE ARRAY AND STORE USER INPUT STRING
                	System.out.println(messagearray[0]);
                // OPTION TO LEAVE A TEXT MESESAGE
	                if (userInput.equals("M")) {
	                	// INITIALIZE ARRAY AND STORE USER INPUT STRING
	            		Scanner scnr2 = new Scanner(System.in);
	            		System.out.println("Enter your message!");
	            		
	            		for (int i = 0; i < messagearray.length; i++) {
	            		messagearray[i] = scnr2.nextLine();
	                    System.out.println("Exiting System. Goodbye");
	                    break;
            		}
                }
                } // END OF NEW CODE
		                if (userInput.equals("E")) {
		                    System.out.println("Exiting System. Goodbye");
		                    break;
		            		}
            }
        }
    
        return;
            }
}