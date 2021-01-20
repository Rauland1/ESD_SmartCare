<%-- 
    Document   : refer_patient
    Created on : 20-Jan-2021, 21:18:02
    Author     : ash
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title>SmartCare - Prescriptions</title>
    </head>
    <body>
        
        <jsp:include page="header.jsp" />
        <main>
            
            <% if(request.getAttribute("msg") != null){ String message = (String) request.getAttribute("msg"); out.println(message);} %>
            <h2>Refer Patient to Specialist ${requestScope.patientName}, Patient ID ${param.patientID}:</h2>
            <form action="PrescriptionServlet.do" method="POST">
                <input type="hidden" name="eName" value="${user.username}">
                <input type="hidden" name="pID" value="${param.patientID}">
                <textarea rows="10" cols="35" name="referral" placeholder="Referral"></textarea><br /> <br />
                <input type="submit" value="Submit Referral" name="add_referral">
            </form>
            
        </main>
        <jsp:include page="footer.jsp" />

    </body>
</html>
