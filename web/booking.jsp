<%-- 
    Document   : booking
    Created on : 11-Dec-2020, 17:49:01
    Author     : Nelson Hobday
--%>

<!-- Include JSTL library -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="css/styles.css"%></style>
        <title>SmartCare - Book Appointment</title>
    </head>
    <body>
        
        <jsp:include page="header.jsp" />
        
        <div class="book_appointment">
            <h3>Please select a date & time:</h3>
            <form action="BookAppointmentServlet.do" method="POST">   
                <div id="select_date">
                    <input type="date" name="date" placeholder="Click to View Calendar" required> <br/><br/>
                </div>
                <div id="select_time">
                    <select name="time">
                        <option value="08:00">08:00</option>
                        <option value="08:30">08:30</option>
                        <option value="09:00">09:00</option>
                        <option value="09:30">09:30</option>
                        <option value="10:00">10:00</option>
                        <option value="10:30">10:30</option>
                        <option value="11:00">11:00</option>
                        <option value="11:30">11:30</option>
                        <option value="12:00">12:00</option>
                        <option value="12:30">12:30</option>
                        <option value="13:00">13:00</option>
                        <option value="13:30">13:30</option>
                        <option value="14:00">14:00</option>
                        <option value="14:30">14:30</option>             
                        <option value="15:00">15:00</option>
                        <option value="15:30">15:30</option>
                        <option value="16:00">16:00</option>
                        <option value="16:30">16:30</option>
                    </select> <br/><br/>
                </div>
                <input type="submit" name="select_appointment" value="Select Appointment">
            </form>
            <br>
            ${requestScope.pr_msg}
        </div>
        
        <div class="appointment_details">
            <h3>Patient Name: ${user.title} ${user.firstName}  ${user.lastName}</h3>
            
            <!-- Do we need to show the patient ID? You can send it further down the request chain using a hidden input if that's your intention-->
            <h3>Patient ID: ${user.id}</h3>  
            
            <h3>Date: ${requestScope.date}</h3>     
            <h3>Time: ${requestScope.time}</h3>  
            
            <form action="BookAppointmentServlet.do" method="POST">
                <input type='hidden' value='${user.id}' name='patientID'>
                <input type='hidden' value='${requestScope.date}' name='hiddenDate'>
                <input type='hidden' value='${requestScope.time}' name ='hiddenTime'>
                <c:if test="${not empty requestScope.pr_msg}">
                    <h3>Staff Name: ${requestScope.staffList}</h3>                  
                    <input type="submit" value="Confirm Booking">
                </c:if>
                <br />                           
            </form>
        </div>
                
        <jsp:include page="footer.jsp" />
        
    </body>
</html>
