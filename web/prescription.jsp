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
            <c:if test="${user.role == 'Nurse' || user.role == 'Doctor'}">
                <h2>Select patient:</h2>
                <form name="patient_prescription_form" action="PrescriptionServlet.do" method="POST">
                    <table>
                        <tr>
                          <th>Patient Name</th>
                          <th>Patient Address</th>
                          <th>Select Patient</th>
                        </tr>
                        <c:forEach items="${patientsList}" var="patient">
                            <tr>
                                <td>${patient.title} ${patient.name}</td>
                                <td>${patient.address}</td>
                                <td><input type="checkbox" name="${patient.id}"></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="submit" name="issue_prescription" value="Submit">
                </form>
            </c:if>
        </main>
        <jsp:include page="footer.jsp" />
    </body>
</html>
