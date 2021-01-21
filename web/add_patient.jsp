<%-- 
    Document   : add_patient
    Created on : 21-Jan-2021, 01:00:17
    Author     : Chris
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="css/styles.css"%></style>
        <title>SmartCare - Add Patient</title>
    </head>
    <body>
        
        <jsp:include page="header.jsp" />
        
        <div class="add_patient">
            <form action="AddPatientServlet.do" method="POST"> 
            <h3>Enter New Patients Details:</h3>
            <label for="fname">Title:</label>           
                <select name="title" type="text" id="title">
                    <option value="Mr">Mr</option>
                    <option value="Mrs">Mrs</option>
                    <option value="Ms">Ms</option>
                    <option value="Miss">Miss</option>
                    <option value="Dr">Dr</option>
                    <option value="Lord">Lord</option>
                    <option value="Master">Master</option>
                </select><br><br>
                <label for="fname">First name:</label>
                <input type="text" id="fname" name="fname" required><br><br>
                <label for="lname">Last name:</label>
                <input type="text" id="lname" name="lname" required><br><br>
                <label for="dob">Date of Birth:</label>
                <input type="date" name="dob" placeholder="Click to View Calendar" required><br><br>
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required><br><br>
                <label for="referred">Referred:</label>
                <input type="text" id="reffered" name="referred"><br><br>
                <label for="type">Type:</label>
                <select name="type" type="text" id="type" required>                    
                    <option value="NHS">NHS</option>
                    <option value="private">Private</option>
                </select>
                <br><br>
                <input type="submit" name="add_patient" value="Add Patient">
            </form>            
        </div>       
        <jsp:include page="footer.jsp" />
        
    </body>
</html>