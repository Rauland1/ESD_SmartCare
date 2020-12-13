<%-- 
    Document   : navbar
    Created on : Dec 12, 2020, 9:30:06 PM
    Author     : ggra9
--%>
<!-- Include JSTL library -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<header class="header_bar">
    <h1 class="header_title">SmartCare - <jsp:getProperty name="user" property="role" /> Dashboard</h1>
    <nav>
        <c:choose>
            <%-- If the role is admin --%>
            <c:when test="${user.getRole() == 'admin'}">
                <ul class="nav_links">
                    <li><a href="dashboard.jsp">Home</a></li>
                    <li><a href="records.jsp">Records</a></li>
                    <li><a href="operations.jsp">Operations</a></li>
                    <li><a href="documents.jsp">Documents</a></li>
                </ul>
            </c:when>
            <%-- If the role is nurse OR doctor --%>
            <c:when test="${user.getRole() == 'nurse' || user.getRole() == 'doctor'}">
                <ul class="nav_links">
                    <li><a href="dashboard.jsp">Home</a></li>
                    <li><a href="timetable.jsp">Timetable</a></li>
                    <li><a href="prescription.jsp">Issue Prescription</a></li>
                </ul>
            </c:when>
            <%-- If the role is patient --%>
            <c:when test="${user.getRole() == 'patient'}">
                <ul class="nav_links">
                    <li><a href="dashboard.jsp">Home</a></li>
                    <li><a href="booking.jsp">Book Appointment</a></li>
                    <li><a href="prescription.jsp">Request Prescription</a></li>
                </ul>
            </c:when>
        </c:choose>
    </nav>
</header>