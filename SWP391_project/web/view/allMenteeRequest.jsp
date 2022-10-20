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
        <table border="2">
            <tr>
                <td>
                    Mentee 
                </td>
                <td> 
                    Mentor
                </td>
                <td>
                    Title
                </td>
                <td>
                    Content
                </td>
                <td> 
                    Status
                </td>
                <td>
                    Skill
                </td>
                <td>
                    Time
                </td>

            </tr>
            <c:forEach var="r" items="${requestScope.requests}">
                <tr>
                    <td>
                        ${r.mentee.user.fullname} 
                    </td>
                    <td> 
                        ${r.mentor.user.fullname}
                    </td>
                    <td>
                        ${r.title}
                    </td>
                    <td>
                        ${r.reqContent}
                    </td>
                    <td> 
                        ${r.status}
                    </td>
                    <td>
                        ${r.skill.skillName}
                    </td>
                    <td>
                        ${r.time}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
