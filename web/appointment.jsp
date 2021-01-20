<%-- 
    Document   : appointments
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
    <main>
    <h2><jsp:getProperty name="user" property="role"/> <jsp:getProperty name="user" property="username"/>'s Appointments</h2>
    <form action="ViewAppointmentServlet.do" method="POST">
        <table>
            <tr>
                <th>Staff</th>
                <th>Date</th>
                <th>Time</th>
                <th>Select</th>
            </tr>
            ${requestScope.aRow}
            <tr>
                <td colspan="3"><input type="submit" name="cancel_appointment" value="cancel"></td>
            </tr>
        </table>
    </form>
    </main>

    <jsp:include page="footer.jsp" />
    <% } else {
            response.sendRedirect("index.jsp");
        }
    %>
</body>
</html>

