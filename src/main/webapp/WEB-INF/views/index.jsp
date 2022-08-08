<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
Hello : ${user}
<table >
        <tr>
            <c:forEach var="user" items="${users}">
                <li><c:out value="${user}" /></li>
            </c:forEach>
        </tr>
</table>
</body>
</html>