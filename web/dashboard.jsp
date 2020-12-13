<%-- 
    Document   : dashboard
    Created on : Nov 27, 2020, 4:58:27 PM
    Author     : Raul-Andrei Ginj-Groszhart
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">

        <title> SmartCare <jsp:getProperty name="user" property="role"/> Dashboard </title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <main>
            <p>Welcome, <jsp:getProperty name="user" property="username" />, this is your new dashboard!</p>

            <form action="LogoutServlet.do" method="POST">
                <input type="submit" name="logout" value="Logout">
            </form>
        </main>
        <jsp:include page="footer.jsp" />
    </body>
</html>
