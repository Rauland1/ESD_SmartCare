<%-- 
    Document   : operations
    Created on : Jan 19, 2021, 6:11:45 PM
    Author     : Alexu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title>SmartCare Operations</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <main>
            <table>
                <tr>
                    <th>Surgery name</th>
                    <th>Duration</th>
                    <th>Prices</th>
                </tr>
                ${requestScope.surgeryPrices}
            </table>
        </main>
        <jsp:include page="footer.jsp" />

    </body>
</html>
