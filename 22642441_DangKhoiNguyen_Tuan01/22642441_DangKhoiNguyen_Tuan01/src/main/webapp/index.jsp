<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<form action="processFormUpLoad" method="post">
    <label>Enter your name:</label>
    <br>
    <label>First:</label>
    <input type="text" id="first" name="first" required>
    <label>Last:</label>
    <input type="text" id="last" name="last" required>
    <br>
    <label>User name:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label>Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <label>Facebook:</label>
    <input type="text" id="facebook" name="facebook">
    <br>
    <label>Short Bio:</label>
    <textarea id="bio" name="bio" rows="4" cols="50" required></textarea>
    <br>
    <button type="submit">Submit</button>
</form>

<button onclick="location.href='login.jsp'">Login</button>
<a href="logout">Logout</a>
<br>
<a href="secure/secret.jsp">Secret Page</a> <br>
<a href="FormUpload.jsp">Upload files</a> <br>
<a href="UploadToDtb.jsp">Upload to database</a> <br>
<a href="MailForm.jsp">Send Mail</a> <br>
</body>
</html>