<%-- 
    Document   : account
    Created on : Dec 16, 2020, 11:43:47 PM
    Author     : ggra9
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title>SmartCare - ${user.firstName}'s Account</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <main>
            <h2>${user.firstName}'s account page</h2>
            <!-- SHOW ALL USER DATA + OPTION TO CHANGE PASSWORD -->
            
        </main>
        <jsp:include page="footer.jsp" />
    </body>
</html>
