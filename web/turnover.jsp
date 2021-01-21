<%-- 
    Document   : turnover
    Created on : 17-Dec-2020, 01:31:19
    Author     : Grant
--%>

<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
    </head>
</head>
<body>
    <%
        if (session.getAttribute("user") != null) {
    %>
    <jsp:include page="header.jsp" />

    <form action="TurnoverServlet.do" method="POST">
        <label for="turnover">Please select a date:</label>
        <input type="date" id="turnover" name="turnover_date" min="2000-01-01" max="2100-12-31" required>          
        <td colspan="3"><input type="submit" name="view_turnover" value="View"></td>
        <table>
            <tr>
                <th>Date</th>
                <th>Sector</th>
                <th>Turnover</th>
            </tr>
            <!--${requestScope.bRow}-->
        </table>
    </form>


    <jsp:include page="footer.jsp" />
    <% } else {
            response.sendRedirect("index.jsp");
        }
    %>
</body>
</html>
