<%-- 
    Document   : navbar
    Created on : Dec 12, 2020, 9:30:06 PM
    Author     : Raul
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
            <c:when test="${user.getRole() == 'Admin'}">
                <ul class="nav_links">
                    <li><a href="dashboard.jsp">Home</a></li>
                    <li><a href="records.jsp">Records</a></li>
                    <li><a href="operations.jsp">Operations</a></li>
                    <li><a href="documents.jsp">Documents</a></li>
                    <li><a href="account.jsp"><jsp:getProperty name="user" property="username" /></a></li>
                </ul>
            </c:when>
            <%-- If the role is nurse OR doctor --%>
            <c:when test="${user.getRole() == 'Nurse' || user.getRole() == 'Doctor'}">
                <ul class="nav_links">
                    <li><a href="dashboard.jsp">Home</a></li>
                    <li><a href="timetable.jsp">Timetable</a></li>
                    <li><a href="PrescriptionServlet.do?listPatients=t">Issue Prescription</a></li>
                    <li><a href="account.jsp"><jsp:getProperty name="user" property="username" /></a></li>
                </ul>
            </c:when>
            <%-- If the role is patient --%>
            <c:when test="${user.getRole() == 'Patient'}">
                <ul class="nav_links">
                    <li><a href="dashboard.jsp">Home</a></li>
                    <li><a href="booking.jsp">Book Appointment</a></li>
                    <li><a href="prescription.jsp">Request Prescription</a></li>
                    <li><a href="account.jsp"><jsp:getProperty name="user" property="username" /></a></li>
                </ul>
            </c:when>
        </c:choose>
    </nav>
</header>