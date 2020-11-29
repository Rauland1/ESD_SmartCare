<%-- 
    Document   : dashboard
    Created on : Nov 27, 2020, 4:58:27 PM
    Author     : Raul-Andrei Ginj-Groszhart
--%>

<% String username = (String) request.getAttribute("username"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">

        <title> Welcome to Dashboard </title>
    </head>
    <body>
        <h1>Welcome, <% out.print(username);%></h1>
    </body>
</html>
