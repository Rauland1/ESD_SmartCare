<%-- 
    Document   : prescription
    Created on : Dec 14, 2020, 1:40:49 PM
    Author     : ggra9
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
            <h2>Issue Prescription for ${requestScope.patientName}, Patient ID ${param.patientID}:</h2>
            <form action="PrescriptionServlet.do" method="POST">
                <input type="hidden" name="eName" value="${user.username}">
                <input type="hidden" name="pID" value="${param.patientID}">
                <textarea rows="10" cols="35" name="prescription" placeholder="Prescription"></textarea><br /> <br />
                <input type="submit" value="Submit Prescription" name="add_prescription">
            </form>
            
        </main>
        <jsp:include page="footer.jsp" />

    </body>
</html>
