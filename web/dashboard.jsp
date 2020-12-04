<%-- 
    Document   : dashboard
    Created on : Nov 27, 2020, 4:58:27 PM
    Author     : Raul-Andrei Ginj-Groszhart
--%>

<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("user");
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">

        <title> Welcome to Dashboard </title>
    </head>
    <body>
        <h1>Welcome, <% out.print(user.username);%></h1>
        
        <p>This is the <% out.print(user.role); %> dashboard</p>
        <form action="UserServlet.do" method="POST">
            <input type="submit" name="logout" value="Logout">
        </form>
    </body>
</html>
