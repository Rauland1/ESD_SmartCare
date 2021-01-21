<%-- 
    Document   : complete_registration
    Created on : Dec 16, 2020, 3:38:32 PM
    Author     : Alexu
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title> SmartCare <jsp:getProperty name="user" property="role"/> Dashboard </title>
        
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <main>
            <h2>Please complete the following fields</h2> 

            <form action="CompleteRegistrationServlet.do" method="POST">
                Title: <br />
                <select name="title" required>
                    <option value="title" disabled selected>Title</option>
                    <option value="Mrs.">Mrs.</option>
                    <option value="Mr.">Mr.</option>
                    <option value="Ms.">Ms.</option>
                    <option value="Dr.">Dr.</option>
                </select><br />
                First Name: <br />
                <input type ="text" placeholder="First Name" name="fName" required>  <br />
                Last Name: <br />
                <input type ="text" placeholder="Last Name" name="lName" required>  <br />
                House Number: <br />
                <input type ="text" placeholder="House number" name="house_no" required>  <br />
                Post Code: <br />
                <input type ="text" placeholder="Post Code" name="post_code" required>  <br />
                Date of birth: <br />
                <input type ="date" placeholder="Date of birth" name="DOB" required>  <br />
                <c:if test="${user.role == 'Nurse' || user.role == 'Doctor'}">
                    Shift start: <br />
                    <input type="time" name="shift_start" min="08:00" max="18:00" required> <br />
                    Shift start: <br />
                    <input type="time" name="shift_end" min="08:00" max="18:00" required><br /><br />
                </c:if>
                <c:if test="${user.role == 'Patient'}">
                    Select your type: <br />
                    <select name="patientType" required>
                        <option value="Type" disabled selected>Type</option>
                        <option value="NHS">NHS</option>
                        <option value="private">Private</option>                       
                    </select> <br /><br />
                </c:if>
                <input name="complete_reg" type="submit" value="Submit">
            </form>
        </main>
        <jsp:include page="footer.jsp" />
    </body>
</html>
