<%-- 
    Document   : prescription
    Created on : Dec 13, 2020, 4:56:02 PM
    Author     : Raul
--%>
<!-- Include JSTL library -->
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
            <h2>Issue patient prescription: </h2>
            <table>
                <tr>
                  <th>Patient ID</th>
                  <th>Patient Name</th>
                </tr>
                <c:forEach items="${patientsList}" var="patient">
                    <tr>
                        <td>${patient.id}</td>
                        <td>${patient.title}, ${patient.name}</td>
                    </tr>
                </c:forEach>
            </table>
            
        </main>
        <jsp:include page="footer.jsp" />
    </body>
</html>
