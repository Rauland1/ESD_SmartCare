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
        <title>SmartCare - Patients</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <main>
            <c:choose>
                <c:when test="${empty param.patientType}">
                    <h2>Select patient type:</h2>
                    <form action="PatientsServlet.do" name="select_patient_type_form" method="GET">
                        <input type="radio" name="patientType" value="all"> All <br />
                        <input type="radio" name="patientType" value="private"> Private <br />
                        <input type="radio" name="patientType" value="nhs"> NHS <br /><br />
                        <input type="submit" name="patient_type_submit" value="Submit">
                    </form>
                </c:when>
                <c:otherwise>
                    <h2>Select patient:</h2>
                    <form name="patient_prescription_form" action="PrescriptionServlet.do" method="POST">
                        <table>
                            <tr>
                                <th>Patient Type</th>
                                <th>Patient Name</th>
                                <th>Patient Address</th>
                                <th>Select Patient</th>
                            </tr>
                            <c:forEach items="${patientsList}" var="patient">
                                <tr>
                                    <td>${patient.type}</td>
                                    <td>${patient.title} ${patient.name}</td>
                                    <td>${patient.address}</td>
                                    <td><input type="checkbox" name="patientID" value="${patient.id}"></td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="4"><input type="submit" name="issue_prescription" value="Issue New Prescription"></td>
                            </tr>
                        </table>
                    </form>
                </c:otherwise>
            </c:choose>
        </main>
        <jsp:include page="footer.jsp" />
    </body>
</html>
