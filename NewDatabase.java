package newzooauthenticationsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NewDatabase {

	public static void main(String args[]){
		
		Connection conn = null;
		try {
		    // Database parameters for URL, username, and password
		    String url       = "jdbc:mysql://localhost:3306/mysqljdbc";
		    String user      = "root";
		    String password  = "secret";
			
		    // Create a connection to the database
		    conn = DriverManager.getConnection(url, user, password);

		} 
		
		catch(SQLException e) {
		   System.out.println(e.getMessage());
		} 
		
		finally {
			try{
		           if(conn != null)
		             conn.close();
		           }
			catch(SQLException ex){
		           System.out.println(ex.getMessage());
			}
		}
	}
}
