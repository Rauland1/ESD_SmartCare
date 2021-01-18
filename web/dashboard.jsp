<%-- 
    Document   : dashboard
    Created on : Nov 27, 2020, 4:58:27 PM
    Author     : Raul-Andrei Ginj-Groszhart
--%>
<% 
//    100% sure this doesn't work but it was worth a try - FILTERS NEED TO BE IMPLEMENTED
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", -1);
    if(session.getAttribute("user") == null){response.sendRedirect("index.jsp");}
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="dashboard_view">        
        <main>
            
            <h2>Welcome, ${user.firstName}!</h2>
            
            <c:choose>
                <c:when test="${user.role == 'Admin'}">
                    ${requestScope.details}
                    ${requestScope.msg}
                    <h2>Approve registrations</h2>
                    <form action='RegisterServlet.do' method='POST'>
                        <table>
                            <tr>
                                <th>Username</th>
                                <th>Role</th>
                                <th>Approve</th>
                            </tr>
                            ${requestScope.regTable}
                            </table>
                        <input type='submit' value='Approve registration' name='approve_reg'>
                    </form> <br /><br />
                </c:when>
                <c:when test="${user.role == 'Nurse' || user.role == 'Doctor'}">
                    ${requestScope.details}
                    ${requestScope.msg}
                    <h2>Approve requests for prescription</h2>
                    <form action='PrescriptionServlet.do' method='POST'>
                        <table>
                            <tr>
                                <th>Patient Name</th>
                                <th>Prescription Details</th>
                                <th>Approve</th>
                            </tr>
                            ${requestScope.presTable}
                            </table>
                        <input type='submit' value='Approve request' name='approve_request'>
                    </form> <br /><br />
                </c:when>
                <c:otherwise>
                    ${requestScope.details}
                    ${requestScope.msg}
                </c:otherwise>
            </c:choose>
            
            <form action="LogoutServlet.do" method="POST">
                <input type="submit" name="logout" value="Logout">
            </form>
        </main>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
