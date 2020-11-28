<%-- 
    Document   : index
    Created on : Nov 27, 2020, 3:56:33 PM
    Author     : Raul-Andrei Ginj-Groszhart

    TODO: - Create session
          - Check if user is logged
          - If not logged in stay on this page
          - If logged in, redirect to dashboard
           
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SmartCare Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>SmartCare Login</h1>
        <!-- Login form that redirects to LoginServlet.doPost() -->
        <form action="LoginServlet.do" name="login_form" method="POST">
            <label for="username">Username</label><br>
            <input name="username" type="text" required><br><br>
            
            <label for="password">Password</label><br>
            <input name="password" type="password" required><br><br>
            
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
