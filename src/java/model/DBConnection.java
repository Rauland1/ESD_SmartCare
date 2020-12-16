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
import javax.servlet.http.HttpSession;
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
    
    public void closeAll(PreparedStatement ps, ResultSet rs) throws SQLException{
        ps.close();
        rs.close();
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
        rs.close();
        statement.close();
        return patientList;
    }
    
    public List staffList() throws SQLException { //NELSON
        List staffList = new ArrayList<>();
        String sql = "SELECT * FROM EMPLOYEE";

        statement = connection.createStatement();
        rs = statement.executeQuery(sql);
        
        while(rs.next()){
            String fName = rs.getString("EFIRST_NAME");
            String lName = rs.getString("ELAST_NAME");
            
            User user = new User(fName, lName);
            staffList.add(user);
        }
        rs.close();
        statement.close();
        return staffList;
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
        
        closeAll(ps, rs);
        return employeeID;
    }
    
    public int grabPatientId(String uname) throws SQLException {
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patients WHERE UNAME=?", PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, uname);

        ResultSet patients = preparedStatement.executeQuery();

        patients.next();

        int id = patients.getInt("pID");
        
        closeAll(preparedStatement, patients);
        return id;
        
    }
    
    public String getEmployeeName (int id) throws SQLException{
        String employeeName = null;
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE eID=?", PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, String.valueOf(id));
        
        ResultSet rs = preparedStatement.executeQuery();
        
        rs.next();
        
        String title = rs.getString("etitle");
        String fName = rs.getString("efirst_name");
        String lName = rs.getString("elast_name");
        
        employeeName = title + ' ' + fName + ' ' + lName;
        
        closeAll(preparedStatement, rs);
        return employeeName;
    }
    
    public String viewPrescriptions(int patientID) throws SQLException{
        StringBuilder prescriptionTable = new StringBuilder();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM prescription WHERE pID=?", PreparedStatement.RETURN_GENERATED_KEYS);
        
        preparedStatement.setString(1, String.valueOf(patientID));
        ResultSet rs = preparedStatement.executeQuery();
        
        while(rs.next()){
            int pres_id = rs.getInt("prID");
            int employee_id = rs.getInt("eID");
            
            String employee_name = getEmployeeName(employee_id);
            
            String prescriptionDetails = rs.getString("prDetails");
            
            String prescriptionRequest = rs.getString("prRequest");
            
            String row = "<tr><td>" + employee_name + "</td><td>" + prescriptionDetails +  "</td><td><input type='checkbox' name='prescriptionID' value='" + pres_id + "'></td><td>" + prescriptionRequest + "</td></tr>";
            
            prescriptionTable.append(row);
        }
        closeAll(preparedStatement, rs);
        return prescriptionTable.toString();
    }
    
    public String checkReg() throws SQLException{
        StringBuilder confirmRegTable = new StringBuilder();
        
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE registration_type=?");
        ps.setString(1, "pending");
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            do {
                String username = rs.getString("uname");
                String role = rs.getString("urole");

                String row = "<tr><td>" + username + "</td><td>" + role + "</td><td><input type='checkbox' name='regUsername' value='" + username + "'></td></tr>";
                confirmRegTable.append(row);
            } while(rs.next());
        }
        
        closeAll(ps, rs);
        
        if(confirmRegTable.length() != 0){
            return confirmRegTable.toString();
        } else {
            String msg = "<td colspan='2'><span>No new accounts to be approved</span></td>";
            confirmRegTable.append(msg);
            return confirmRegTable.toString();
        }
        
    }
    
    public void confirmReg(String username) throws SQLException
    {
        PreparedStatement ps = connection.prepareStatement("UPDATE users SET registration_type='confirmed' WHERE uname=?");
        
        ps.setString(1, username);
        ps.executeUpdate();
        
        ps.close();
    }
    
    public void updatePrescription(String prescriptionID){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE prescription SET prREQUEST='requested' WHERE prID=?", PreparedStatement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, Integer.parseInt(prescriptionID));
           
            preparedStatement.executeUpdate();
 
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String checkPrescription(String username) throws SQLException {
        StringBuilder confirmPrescriptionTable = new StringBuilder();
        
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM prescription WHERE eID=? AND prRequest='requested'");
        ps.setString(1, getEmplyeeIDFromUsername(username));
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            do {
                int patientID = rs.getInt("pID");
                String patient_name = getPatientNameFromID(patientID);
                String prescription_details = rs.getString("prDetails");
                int prID = rs.getInt("prID");
                
                String row = "<tr><td>" + patient_name + "</td><td>" + prescription_details + "</td><td><input type='checkbox' name='presID' value='" + prID + "'></td></tr>";
                confirmPrescriptionTable.append(row);
            } while(rs.next());
        }
        
        closeAll(ps, rs);
        return confirmPrescriptionTable.toString();
    }
    
    public void confirmPrescription(int prID) throws SQLException
    {
        PreparedStatement ps = connection.prepareStatement("UPDATE prescription SET prRequest='confirmed' WHERE prID=?");
        
        ps.setInt(1, prID);
        ps.executeUpdate();
        
        ps.close();
    }
    
    public String getPatientNameFromID(int pID) throws SQLException{
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patients WHERE PID=?", PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, pID);

        ResultSet patients = preparedStatement.executeQuery();

        patients.next();

        String title = patients.getString("pTitle");
        String fName = patients.getString("pFirst_Name");
        String lName = patients.getString("pLast_Name");
        String fullName = title + ' ' + fName + ' ' + lName;

        closeAll(preparedStatement, patients);
        return fullName;
        
    }
    
    public String checkFields (String uname, String urole) throws SQLException {        
        
        String message = null;
        String sql = null;
        String dbn = null;
        
        if(urole.equals("Admin") || urole.equals("Doctor") || urole.equals("Nurse")){
            sql = "SELECT * FROM employee WHERE uname=?";
            dbn = "e";
        }
        else {
            sql = "SELECT * FROM patients WHERE uname=?";
            dbn = "p";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, uname);

        ResultSet user = preparedStatement.executeQuery();

        if(user.next()){

            String[] details = new String[4];

            details[0] = user.getString(dbn + "Title");
            details[1] = user.getString(dbn + "First_Name");
            details[2] = user.getString(dbn + "Last_Name");
            details[3] = user.getString(dbn + "Address");

            for(String detail : details){
                if(detail.equals("") || detail.isEmpty()){
                    message = "Your profile is not complete. Please follow <a href='RegisterServlet.do?completeRegistration=true'>this link</a> to update your details.";
                    break;
                }
            }
        }
        
        if(message != null){
            return message;
        } else {
            return "";
        }
    }
    
      public void completeRegistration(String details[], String username, String urole){
        try {
            
            String sql = null;
            
            if(urole.equals("Admin") || urole.equals("Doctor") || urole.equals("Nurse")) {
                sql = "UPDATE employee SET eTitle=?, eFirst_Name=?, eLast_Name=?, eAddress=? WHERE uname=?";
            }
            else {
                sql = "UPDATE patients SET pTitle=?, pFirst_Name=?,pLast_Name=?, pAddress=? WHERE uname=?";
            }
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, details[0]);
            preparedStatement.setString(2, details[1]);
            preparedStatement.setString(3, details[2]);
            
            PlaceAPI placeapi = new PlaceAPI(details[4]);
            
            String full_address = details[3] + " " + placeapi.getAddressXML();
            
            preparedStatement.setString(4, full_address);
            
            preparedStatement.setString(5, username);
            
            preparedStatement.executeUpdate();
 
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
