<%-- 
    Document   : view_prescriptions
    Created on : Dec 14, 2020, 8:40:02 PM
    Author     : Alexu
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title>SmartCare - View Prescriptions</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <main>
            <h2>Your Prescriptions</h2>
            <form action="PrescriptionServlet.do" method="POST">
            <table>
                <tr>
                    <th>Issued By</th>
                    <th>Prescription Details</th>
                    <th>Select Prescription</th>
                </tr>
                ${requestScope.pRow}
                <tr>
                    <td colspan="3"><input type="submit" name="request_prescription" value="Request Prescription"></td>
                </tr>
            </table>
            </form>
        </main>
        <jsp:include page="footer.jsp" />
    </body>
</html>
