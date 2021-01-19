<%-- 
    Document   : timetable
    Created on : 14-Dec-2020, 9:18:02
    Author     : Ash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title><jsp:getProperty name="user" property="username"/>'s Appointments </title>
    </head>
</head>
<body>
    <%
        if (session.getAttribute("user") != null) {
    %>
    <jsp:include page="header.jsp" />

    <h2><jsp:getProperty name="user" property="role"/> <jsp:getProperty name="user" property="username"/>'s Appointments</h2>
    <form action="BookingsServlet.do" method="POST">
        <label for="bookings">Please select a date:</label>
        <input type="date" id="bookings" name="app_date" min="2000-01-01" max="2100-12-31" required>          
        <td colspan="3"><input type="submit" name="view_bookings" value="View"></td>
        <table>
            <tr>
                <th>Patient</th>
                <th>Date</th>
                <th>Time</th>
            </tr>
            ${requestScope.bRow}
            <tr>

            </tr>
        </table>
    </form>


    <jsp:include page="footer.jsp" />
    <% } else {
            response.sendRedirect("index.jsp");
        }
    %>
</body>
</html>

