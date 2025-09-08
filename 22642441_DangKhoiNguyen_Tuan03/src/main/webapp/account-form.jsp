<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/8/2025
  Time: 8:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AccountRegister</title>
</head>
<body>
<h1 style="text-align: center;">User Registration Form</h1>
<form action="account" method="post">
    <label style="width: 200px">First Name:</label>
    <input type="text" name="firstName" width="300" required>
    <label style="width: 200px">Last Name:</label>
    <input type="text" name="lastName" width="300" required> <br>
    <label style="width: 200px">Password:</label>
    <input type="password" name="password" width="300" required> <br>
    <label style="width: 200px">Email:</label>
    <input type="email" name="email" width="300" required> <br>
    <label style="width: 200px">Date of Birth:</label>
    <input type="date" name="dob" width="600" required> <br>
    <input type="submit" value="Register">
</form>
</body>
</html>
