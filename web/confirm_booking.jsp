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
        <title>SmartCare - Booking Confirmed</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <h1>Booking confirmed!</h1>
        <h3><%=request.getAttribute("staffName")%></h3
        <h3><%=request.getAttribute("EID")%></h3>
        <h3><%=request.getAttribute("PID")%></h3>
        <jsp:include page="footer.jsp" />
    </body>
</html>
