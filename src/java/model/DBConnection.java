package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Raul-Andrei Ginj-Groszhart
 */

public class DBConnection {
 
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    
    public DBConnection() {}
 
    public DBConnection(Connection _connection) {
        connection = _connection;
    }
    
    public void connect(Connection _con)
    {
        connection = _con;
    }
 
    // Insert user records into database
    public void insert(String[] str) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
 
            preparedStatement.setString(1, str[0].trim());
            preparedStatement.setString(2, str[1]);
            preparedStatement.setString(3, str[2]);
 
            preparedStatement.executeUpdate();
 
            preparedStatement.close();
 
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    // Log user in and return boolean
    public boolean login(String[] str) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE UNAME=? AND PASSWD=?", PreparedStatement.RETURN_GENERATED_KEYS);
 
        preparedStatement.setString(1, str[0]);
        preparedStatement.setString(2, str[1]);
 
        ResultSet results = preparedStatement.executeQuery();
 
        return results.next();
    }
 
    // Check if username already exists in the database
    public boolean exists(String user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE UNAME=?", PreparedStatement.RETURN_GENERATED_KEYS);
 
            preparedStatement.setString(1, user);
 
            ResultSet users = preparedStatement.executeQuery();
 
            return users.next();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return false;
    }
    
    public List<Patient> patientsList(String patientType) throws SQLException {
        List<Patient> patientList = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        
        if("all".equals(patientType)){
            sql = "SELECT * FROM patients";
        } else if("nhs".equals(patientType)) {
            sql = "SELECT * FROM patients WHERE pType='NHS'";
        } else if("private".equals(patientType)) {
            sql = "SELECT * FROM patients WHERE pType='private'";
        }
        statement = connection.createStatement();
        rs = statement.executeQuery(sql);
        
        while(rs.next()){
            int id = rs.getInt("pID");
            String title = rs.getString("pTitle");
            String fName = rs.getString("pFirst_name");
            String lName = rs.getString("pLast_name");
            String addr = rs.getString("pAddress");
            String type = rs.getString("pType");
            
            Patient patient = new Patient(id, title, fName, lName, addr, type);
            patientList.add(patient);
        }
        
        return patientList;
    }
    
    // Create new User object
    public User grabUserByName(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE UNAME=?", PreparedStatement.RETURN_GENERATED_KEYS);
 
            preparedStatement.setString(1, username);
 
            ResultSet users = preparedStatement.executeQuery();
            
            users.next();
            
            String uname = users.getString("uname");
            String role = users.getString("urole");
            
            return new User(uname, role);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return null;
    }
    
    public void insertPrescription(String prescription[]){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO prescription (oid, eid, pid, prdetails) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, "14");
            preparedStatement.setString(2, prescription[0]);
            preparedStatement.setString(3, prescription[1]);
            preparedStatement.setString(4, prescription[2]);
            
            preparedStatement.executeUpdate();
 
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getEmplyeeIDFromUsername(String username) throws SQLException{
        
        username = username.toLowerCase();
        String employeeID = null;

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE UNAME=?", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            int eID = rs.getInt("eid");
            employeeID = String.valueOf(eID);
        }

        return employeeID;
    }
}