package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;
import model.BookingSlot;

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
 
    // Insert new user into the database
    public void insert(String[] str) { 
        try {
            
            String sql = null;
            String type = null;
            
            if(str[2].equals("patient"))
            {
                sql = "INSERT INTO patients (uname) VALUES (?)";
                type = "confirmed";
            } else {
                sql = "INSERT INTO employee (uname) VALUES (?)";
                type = "pending";
            }
            
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
 
            preparedStatement.setString(1, str[0].trim());
            preparedStatement.setString(2, str[1]);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, str[2]);
 
            preparedStatement.executeUpdate();
 
            preparedStatement.close();
            
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim());
            ps.executeUpdate();
            ps.close();
 
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
    
    /**
     *
     * @param day
     * @param time
     * @return
     * @throws SQLException
     */
    public String staffList(String day, String time) throws SQLException { // Added time as input

        StringBuilder availableStaff = new StringBuilder();      
        String sql = "SELECT * FROM EMPLOYEE WHERE (EDAYS LIKE '%"+day+"%') AND (ESHIFT_START < ('"+time+"'))"; // Revised SQL to show correctly scheduled staff

        statement = connection.createStatement();
        rs = statement.executeQuery(sql);
        
        availableStaff.append("<select class=\"form-control\" name=\"staff\" id=\"staff\">");
        availableStaff.append("<option disabled selected hidden>Choose a doctor or nurse</option>");
        while(rs.next()){
            String title = rs.getString("ETITLE");
            String fName = rs.getString("EFIRST_NAME");
            String lName = rs.getString("ELAST_NAME");
            int ID = rs.getInt("EID");
            String name = (ID + " " + title + " " + fName + " " + lName);
            String row = ("<option name=\"staff\" value=\""+name+"\"\">" +name+ "</option> ");
            availableStaff.append(row);
        }        
        availableStaff.append("</select>");
        rs.close();
        statement.close();
        
        return availableStaff.toString();        
    }
    
    public boolean insertBooking(String details[]) throws ParseException{

        //Date sqlDate = Date.valueOf(details[2]);
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO BOOKING_SLOTS (EID, PID, SDATE, STIME) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, details[0]);
                preparedStatement.setString(2, details[1]);
                preparedStatement.setString(3, details[2]);
                preparedStatement.setString(4, details[3]);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
            String capitalRole = role.substring(0,1).toUpperCase() + role.substring(1);
            String[] userDetails = getUserDetails(uname, capitalRole);
            
            if(userDetails == null)
                return new User(uname, role);
            else
                return new User(uname, role, userDetails[0], userDetails[1], userDetails[2], Integer.parseInt(userDetails[3]));
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return null;
    }
    
    public String[] getUserDetails(String username, String role) throws SQLException{
        String sql;
        String dbn;
        
        String[] details = new String[4];
        
        if(role.equals("Admin") || role.equals("Doctor") || role.equals("Nurse")){
            sql = "SELECT * FROM employee WHERE uname=?";
            dbn = "e";
        }
        else {
            sql = "SELECT * FROM patients WHERE uname=?";
            dbn = "p";
        }
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, username);

        ResultSet user = preparedStatement.executeQuery();
        
        if(user.next()){
            
            details[0] = user.getString(dbn + "Title");
            details[1] = user.getString(dbn + "First_Name");
            details[2] = user.getString(dbn + "Last_Name");
            details[3] = String.valueOf(user.getInt(dbn + "ID"));
            
            if(user.wasNull()){
                return null;
            }
        }
        
        return details;
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
    
    public String getPatientTypeFromID(int patientId) throws SQLException{
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patients WHERE PID=?", PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, patientId);

        ResultSet patients = preparedStatement.executeQuery();

        patients.next();

        String patientType = patients.getString("ptype");

        closeAll(preparedStatement, patients);
        
        return patientType;
        
    }
    
    public String checkFields (String uname, String urole) throws SQLException {        
        
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
            
            if(user.wasNull()){
                String message = "Your profile is not complete. Please follow <a href='RegisterServlet.do?completeRegistration=true'>this link</a> to update your details.<br /><br />";
                return message;
            } else {      
                return "";
            }
        }
        
        return "";
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
    
    public float getTurnoverForDay(Date day) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String stringDate = formatter.format(day);
            
            String sql = "SELECT * FROM booking_slots WHERE sDate = '" + stringDate + "'";//2020-11-01 - 2020-12-00
            
            PreparedStatement queryStatement = connection.prepareStatement(sql);
            
            ResultSet results = queryStatement.executeQuery();
            
            List<BookingSlot> bookingSlots = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            
            while (results.next()) {
                int id = results.getInt(1);
                int employeeId = results.getInt(2);
                int patientId = results.getInt(3);
                Date resultDate = results.getDate(4);
                java.sql.Time resultTime = results.getTime(5);
                
                ids.add(String.valueOf(id));
                bookingSlots.add(new BookingSlot(id, employeeId, patientId, resultDate, resultTime));
            }
            
            String idsCommaSeperated = String.join(",", ids);
            String secondSql = "SELECT * FROM operations WHERE sID IN (" + idsCommaSeperated + ")"; //7,8,9
           
            PreparedStatement state = connection.prepareStatement(secondSql);
            
            ResultSet moreResults = state.executeQuery();
            
            List<Operation> operations = new ArrayList<>();
            
            while (moreResults.next()) {
                int id = moreResults.getInt(1);
                int bookingSlotId = moreResults.getInt(2);
                float duration = moreResults.getFloat(3);
                float charge = moreResults.getFloat(4);
                
                operations.add(new Operation(id, bookingSlotId, duration, charge));
            }
            
            float total = 0;
            
            for (Operation operation : operations) {
                total += operation.charge;
            }
            
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    enum PatientType {
        All, NHS, Private
    }
    
    public float getTurnoverForRange(Date startDate, Date endDate, PatientType patientType) {
        try {
            String extraSql = " AND (SELECT patients.PTYPE FROM patients WHERE patients.PID = booking_slots.PID) = '&s'";
            
            switch (patientType) {
                case Private:
                    extraSql = String.format(extraSql, "private");
                    break;
                case NHS:
                    extraSql = String.format(extraSql, "NHS");
                    break;
            }
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String stringStartDate = formatter.format(startDate);
            String stringEndDate = formatter.format(endDate);
            
            String sql = String.format("SELECT * FROM booking_slots WHERE sDate >= '%s' AND sDate <= '%s'", stringStartDate, stringEndDate);
            
            if (patientType != PatientType.All) {
                sql = sql + extraSql;
            }
            
            PreparedStatement queryStatement = connection.prepareStatement(sql);
            
            ResultSet results = queryStatement.executeQuery();
            
            List<BookingSlot> bookingSlots = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            
            while (results.next()) {
                int id = results.getInt(1);
                int employeeId = results.getInt(2);
                int patientId = results.getInt(3);
                Date resultDate = results.getDate(4);
                java.sql.Time resultTime = results.getTime(5);
                
                // Filter out if patient type matches
                
                ids.add(String.valueOf(id));
                bookingSlots.add(new BookingSlot(id, employeeId, patientId, resultDate, resultTime));
            }
            
            String idsCommaSeperated = String.join(",", ids);
            String secondSql = "SELECT * FROM operations WHERE sID IN (" + idsCommaSeperated + ")"; //7,8,9
           
            PreparedStatement state = connection.prepareStatement(secondSql);
            
            ResultSet moreResults = state.executeQuery();
            
            List<Operation> operations = new ArrayList<>();
            
            while (moreResults.next()) {
                int id = moreResults.getInt(1);
                int bookingSlotId = moreResults.getInt(2);
                float duration = moreResults.getFloat(3);
                float charge = moreResults.getFloat(4);
                
                operations.add(new Operation(id, bookingSlotId, duration, charge));
            }
            
            float total = 0;
            
            for (Operation operation : operations) {
                total += operation.charge;
            }
            
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
}
