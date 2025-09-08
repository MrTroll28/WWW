<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/8/2025
  Time: 8:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Result</title>
</head>
<body>
<h1 style="text-align: center;">Student Register Result</h1>
<p>First Name: <b>${student.firstName}</b></p>
<p>Last Name: <b>${student.lastName}</b></p>
<p>Date of Birth: <b>${student.dob}</b></p>
<p>Email: <b>${student.email}</b></p>
<p>Phone: <b>${student.phone}</b></p>
<p>Gender: <b>${student.gender}</b></p>
<p>Address: <b>${student.address}</b></p>
<p>City: <b>${student.city}</b></p>
<p>Pin Code: <b>${student.pinCode}</b></p>
<p>State: <b>${student.state}</b></p>
<p>Country: <b>${student.country}</b></p>
<p>Hobbies:</p>
    <ul>
        <c:forEach var="h" items="${student.hobbies}">
            <li>${h}</li>
        </c:forEach>
    </ul>
</body>
</html>
