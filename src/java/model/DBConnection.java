package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Raul-Andrei Ginj-Groszhart
 */

public class DBConnection {

  private Connection con;
  private Statement state;
  private ResultSet rs;

  public String doQuery(String username, String password) {
      
    StringBuilder sb = new StringBuilder();
    String sqlQuery = "SELECT * from users WHERE UNAME='" + username + "' AND PASSWD='" + password +"'";
    
    try {
        // Create connection to database
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/SmartCare", "assignment", "password");
        state = con.createStatement();
        // Query to execute
        rs = state.executeQuery(sqlQuery);
        
        // If the username and password match
        if(rs.next())
        {
            // Append the username to the sb string
            sb.append(username);
        }
        else
        {
            // If no username is found
            sb.append("No such user exists!");
        }
        
        // Close the resultset, state and connection
        rs.close();
        state.close();
        con.close();
      
    } catch (ClassNotFoundException | SQLException e) {
        System.err.println("Error: " + e);

    }
    
    // Return the username string
    return sb.toString();
  }
  
} 