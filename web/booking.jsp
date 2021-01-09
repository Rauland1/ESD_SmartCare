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
        <h3>Please select a date:</h3>  
        <pre>
        
        <form action="BookAppointmentServlet.do">            
            <input type="date" name="date" placeholder="Click to View Calendar" id="datepicker"> 
            <br>
            <input type="submit" value="Select Date">
            <br>                  
        </form>  
        
        </pre> 
        
        <h3>Patient Name: <jsp:getProperty name="user" property="username"/></h3>
        <h3>Patient ID: <%=(request.getAttribute("pID"))%></h3>
        <h3>Date: <%=request.getAttribute("date")%></h3>         
        <form action="BookAppointmentServlet.do">
            <% if (request.getAttribute("date") != null){
                %><h3>Staff Name: <%=request.getAttribute("staff")%></h3>                  
                <input type="submit" value="Confirm Booking"><%
            }%>
            <br>                           
        </form>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
