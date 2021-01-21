<%-- 
    Document   : records.jsp
    Created on : Jan 20, 2021, 2:16:16 AM
    Author     : Alexu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title>SmartCare - Delete Appointments</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <h2>Appointments</h2>
        <form action="AdminServlet.do" method="POST">
            <table>
                <tr>
                    <th>Doctor</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Select</th>
                </tr>
                ${requestScope.bookingRows}
                <tr>
                    <td><input type="submit" name="delete_booking" value="Delete"></td>
                </tr>
            </table>
        </form>


        <jsp:include page="footer.jsp" />
    
    </body>
</html>
