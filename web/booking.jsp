<%-- 
    Document   : booking
    Created on : 11-Dec-2020, 17:49:01
    Author     : Nelson Hobday
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="css/styles.css"%></style>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
        <title>SmartCare - Create Booking</title>
    </head>
    <body>
    <jsp:include page="header.jsp" />
    <div class="book_appointment">
    <h3>Please select a date & time:</h3><pre><form action="BookAppointmentServlet.do">   
    <div id="select_date">
<input type="date" name="date" placeholder="Click to View Calendar" id="datepicker"></div>
    <div id="select_time">
<select name="time">
        <option>08:00</option>
        <option>08:30</option>
        <option>09:00</option>
        <option>09:30</option>
        <option>10:00</option>
        <option>10:30</option>
        <option>11:00</option>
        <option>11:30</option>
        <option>12:00</option>
        <option>12:30</option>
        <option>13:00</option>
        <option>13:30</option>
        <option>14:00</option>
        <option>14:30</option>             
        <option>15:00</option>
        <option>15:30</option>
        <option>16:00</option>
        <option>16:30</option>                 
        </select>
        </div>
<input type="submit" value="Select Appointment">    
        
        </form>
<%if (request.getAttribute("pr_msg")!= null){
        %><h3><%=request.getAttribute("pr_msg")%></h3><%
        }%> 
        </div> 
        </pre> 
        <div class="appointment_details">
        <h3>Patient Name: <jsp:getProperty name="user" property="username"/></h3>
        <h3>Patient ID: <%=(request.getAttribute("pID"))%></h3>
        <h3>Date: <%=request.getAttribute("date")%></h3>     
        <h3>Time: <%=session.getAttribute("time")%></h3>  
        <form action="BookAppointmentServlet.do">
            <% if (request.getAttribute("pr_msg") != null) {
                %><h3>Staff Name: <%=request.getAttribute("staff")%></h3>                  
                <input type="submit" value="Confirm Booking"><%
            }%>
            <br>                           
        </form>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
