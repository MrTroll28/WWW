<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/8/2025
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Account Result</title>
</head>
<body>
<h1 style="text-align: center;">Account Register Result</h1>

<table border="1" cellpadding="10" cellspacing="0" style="margin: 0 auto;">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Date of Birth</th>
    </tr>
    <c:forEach var="acc" items="${accounts}">
        <tr>
            <td>${acc.id}</td>
            <td>${acc.firstName}</td>
            <td>${acc.lastName}</td>
            <td>${acc.email}</td>
            <td>${acc.dateOfBirth}</td>
            <td>${acc.password}</td>
        </tr>
    </c:forEach>
</table>

<p style="text-align: center; margin-top: 20px;">
    <a href="register.jsp">Register another account</a>
</p>

</body>
</html>