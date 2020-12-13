<%-- 
    Document   : navbar
    Created on : Dec 12, 2020, 9:30:06 PM
    Author     : ggra9
--%>
<!-- Include JSTL library -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%--<jsp:getProperty name="user" property="username" />--%>
<c:choose>
    <c:when test="${user.getRole() == 'admin'}">
        <ul>
            <jsp:getProperty name="user" property="username" />
            <a href="records.jsp">Records</a>
            <a href="operations.jsp">Operations</a>
            <a href="documents.jsp">Documents</a>
        </ul>
    </c:when>
        
    <c:when test="${user.getRole() == 'nurse' || user.getRole() == 'doctor'}">
        <ul>
            <jsp:getProperty name="user" property="username" />
            <a href="timetable.jsp">Timetable</a>
            <a href="prescription.jsp">Issue Prescription</a>
        </ul>
    </c:when>
        
    <c:when test="${user.getRole() == 'patient'}">
        <ul>
            <jsp:getProperty name="user" property="username" />
            <a href="booking.jsp">Book Appointment</a>
            <a href="prescription.jsp">Request Prescription</a>
        </ul>
    </c:when>
</c:choose>