<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/8/2025
  Time: 8:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student register form</title>
</head>
<body>
<h1 style="text-align: center;">Student Register Form</h1>
<form action="student" method="get">
    <label style="width: 200px">First Name:</label>
    <input type="text" name="firstName" width="300" required>
    <label style="width: 200px">Last Name:</label>
    <input type="text" name="lastName" width="300" required> <br>
    <label style="width: 200px">Email:</label>
    <input type="email" name="email" width="300" required>
    <label style="width: 200px">Phone:</label>
    <input type="text" name="phone" width="300" required> <br>
    <label style="width: 200px">Date of Birth:</label>
    <input type="date" name="dob" width="600" required> <br>
    <label style="width: 200px">Email</label>
    <input type="email" name="email" width="300" required>
    <label style="width: 200px">Mobile</label>
    <input type="text" name="phone" width="300" required> <br>
    <label style="width: 200px">Gender</label>
    <input type="radio" name="gender" value="Male">
    <input type="radio" name="gender" value="Female"> <br>
    <label style="width: 200px">Address</label>
    <input type="text" name="address" width="600" required> <br>
    <label style="width: 200px">City</label>
    <input type="text" name="city" width="300" required>
    <label style="width: 200px">Pin Code</label>
    <input type="text" name="pinCode" width="300" required> <br>
    <label style="width: 200px">State</label>
    <input type="text" name="state" width="300" required>
    <label style="width: 200px">Country</label>
    <input type="text" name="country" width="300" required> <br>
    <label style="width: 200px">Hobbies</label>
    <input type="checkbox" name="hobbies" value="Reading">Reading
    <input type="checkbox" name="hobbies" value="Traveling">Traveling
    <input type="checkbox" name="hobbies" value="Sports">Sports
    <input type="checkbox" name="hobbies" value="Music">Music <br>
    <input type="checkbox" name="hobbies" value="Others">Others
    <input type="submit" value="Submit">
</form>
</body>
</html>
