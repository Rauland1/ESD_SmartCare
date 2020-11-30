package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raul-Andrei Ginj-Groszhart
 */

public class DBConnection {
    
    Connection con = null;
    Statement state = null;
    ResultSet rs = null;
   
    public void connect(Connection _con)
    {
       con = _con;
    }
  
    private void select(String query){
        //Statement statement = null;
        
        try {
            state = con.createStatement();
            rs = state.executeQuery(query);
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
  
    // Insert user records into database
    public void insert(String[] str){
      PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO users VALUES (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim()); 
            ps.setString(2, str[1]);
            ps.setString(3, str[2]);
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    // Log user in and return boolean
    public boolean login(String[] str) throws SQLException{
        String loginQuery = "SELECT * FROM users WHERE UNAME='" + str[0] + "' AND PASSWD='" + str[1] +"'";
    
        state = con.createStatement();
        rs = state.executeQuery(loginQuery);

        // If the username and password match
        if(rs.next())
        {
            return true;
        }
        return false;
    }
  
    // Check if username already exists in the database
    public boolean exists(String user) {
        boolean bool = false;
        try  {
            select("select UNAME from users where UNAME='"+user+"'");
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
  
} 