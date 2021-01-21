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
    <h1 class="header_title">SmartCare - ${user.role} Dashboard</h1>
    <nav>
        <c:choose>
            <%-- If the role is admin --%>
            <c:when test="${user.role == 'Admin'}">
                <ul class="nav_links">
                    <li><a href="DashboardServlet.do">Home</a></li>
                    <li><a href="AdminServlet.do?viewAppointments=true">Delete Appointments</a></li>
                    <li><a href="AdminServlet.do?viewSurgeryPrices=true">Surgery Prices</a></li>
                    <li><a href="documents.jsp">Documents</a></li>
                    <li><a href="account.jsp">${user.firstName}</a></li>
                    <li><a href="turnover.jsp">turnover</a></li>
                </ul>
            </c:when>
            <%-- If the role is nurse OR doctor --%>
            <c:when test="${user.role == 'Nurse' || user.role == 'Doctor'}">
                <ul class="nav_links">
                    <li><a href="DashboardServlet.do">Home</a></li>
                    <li><a href="timetable.jsp">Timetable</a></li>
                    <li><a href="PatientsServlet.do">View Patients</a></li> 
                    <li><a href="AddPatientServlet.do">Add Patient</a></li> 
                    <li><a href="account.jsp">${user.firstName}</a></li>
                </ul>
            </c:when>
            <%-- If the role is patient --%>
            <c:otherwise>
                <ul class="nav_links">
                    <li><a href="DashboardServlet.do">Home</a></li>
                    <li><a href="BookAppointmentServlet.do">Book Appointment</a></li>
                    <li><a href="PrescriptionServlet.do?viewPrescription=true">View Prescriptions</a></li>
                    <li><a href="ViewAppointmentServlet.do?view_appointments=true">Appointments</a></li>
                    <li><a href="account.jsp">${user.firstName}</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
    </nav>
</header>
