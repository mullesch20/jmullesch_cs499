package newzooauthenticationsystem;
import newzooauthenticationsystem.PasswordHash;
import java.io.File;
import java.util.Scanner;

public class NewAuthenticator 
{
    static String userRole;
    // INTIALIZE VARIABLE FOR ACCESS IN THE PASSWORDHASH CLASS
    static String userPassword;
    
        public NewAuthenticator() 
        {
            userRole = "None";          
        }
        
        // method to authenticate credential information
        public static void authenticate(String userName, String userPassword) throws Exception
        {   

            // read input from credentials database
            Scanner scnr = new Scanner (new File("credentials.txt"));
            String lineText;
            String hashedPassword = "";
            // create array with enough memory allocations for 4 indexes
            String[] userInfoArray = new String[4];
            // while loop to read the credentials file
            while (scnr.hasNextLine()) 
            {
                lineText = scnr.nextLine();
                // separate the substrings by the tabs in the strings
                userInfoArray =  lineText.split("\t"); 
                // compare user input to the 0 index of the array
                if (userInfoArray[0].equals(userName)) 
                {   // constructor for getting hashed password
                    hashedPassword = PasswordHash.generateHash(userPassword);
                    break;
                }
            }
            // add .trim to cut the white space from the substrings
            if (hashedPassword.equals(userInfoArray[1].trim())) 
            {
                userRole = userInfoArray[3].trim();
            }
        }// member method to return the value of userRole
        public static String getUserRole()
        {
            return userRole;
        }

}
