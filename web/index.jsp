<%-- 
    Document   : index
    Created on : Nov 27, 2020, 3:56:33 PM
    Author     : Raul-Andrei Ginj-Groszhart & Ashley Gregory

    TODO: - Create session
          - Check if user is logged
          - If not logged in stay on this page
          - If logged in, redirect to dashboard
           
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/css/styles.css"%></style>
        <script src="https://kit.fontawesome.com/6b0d0ffdec.js" crossorigin="anonymous"></script>
        <title>SmartCare Login</title>
    </head>

    <body>
        <header>
            <div class="login-box">
              <h1>Login</h1>
                <!-- Login form that redirects to LoginServlet.doPost() -->
                <form action="LoginServlet.do" name="login_form" method="POST">
                    
                    
                    <div class="textbox">
                        <i class="fas fa-user"></i>
                        <input type ="text" placeholder="Username" name="username" required>
                    </div>
                    <div class="textbox">
                        <i class="fas fa-lock"  ></i>
                        <input type="password" placeholder="Password" name="password" required>
                    </div>
                    <input class="btn" type="submit" value="Sign in">
                </form>
            </div>
        </header>
    </body>
</html>
