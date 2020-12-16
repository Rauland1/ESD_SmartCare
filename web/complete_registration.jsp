<%-- 
    Document   : complete_registration
    Created on : Dec 16, 2020, 3:38:32 PM
    Author     : Alexu
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
            <h2>Please complete the following fields</h2> 

            <form action="RegisterServlet.do" method="POST">
                Title: <br />
                <select name="title" required>
                    <option value="title" disabled selected>Title</option>
                    <option value="Mrs.">Mrs.</option>
                    <option value="Mr.">Mr.</option>
                    <option value="Ms.">Ms.</option>
                    <option value="Dr.">Dr.</option>
                </select><br />
                First Name: <br />
                <input type ="text" placeholder="First Name" name="fName" required>  <br />
                Last Name: <br />
                <input type ="text" placeholder="Last Name" name="lName" required>  <br />
                House Number: <br />
                <input type ="text" placeholder="House number" name="house_no" required>  <br />
                Post Code: <br />
                <input type ="text" placeholder="Post Code" name="post_code" required>  <br /><br />
                <input name="complete_reg" type="submit" value="Submit">
            </form>
        </main>
        <jsp:include page="footer.jsp" />
    </body>
</html>
