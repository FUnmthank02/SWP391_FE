<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : allMenteeRequest
    Created on : Oct 20, 2022, 11:13:51 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Requests per User per Day</p>
        <table border="2">
            <tr>
                <td>
                    Date
                </td>
                <td>
                    Requests per User per Day
                </td>
            </tr>
            <c:forEach var="a" items="${requestScope.averageRequest}">
                <tr>
                    <td>
                        ${a.key}
                    </td>
                    <td>
                        ${a.value}
                    </td>
                </tr>
            </c:forEach>        
        </table>
        <p>Count requests per day</p>
        <table border="2">
            <tr>
                <td>
                    Date
                </td>
                <td>
                    Request
                </td>
            </tr>
            <c:forEach var="c" items="${requestScope.countRequest}">
                <tr>
                    <td>
                        ${c.key}
                    </td>
                    <td>
                        ${c.value}
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>Responsed Request Percentage</p>
        <table border="2">
            <tr>
                <td>
                    Not responsed
                </td>
                <td>
                    Reponsed
                </td>
            </tr>
            <c:forEach var="p" items="${requestScope.percentage}">
                <tr>
                    <td>
                        ${p}
                    </td>
                    <td>
                        ${p}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
