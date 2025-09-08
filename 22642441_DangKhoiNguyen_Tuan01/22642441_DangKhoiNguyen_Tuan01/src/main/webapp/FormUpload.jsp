<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 8/20/2025
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload files</title>
</head>
<body>
<h1>Upload multi-files</h1>
<form action="UploadServlet" method="post" enctype="multipart/form-data">
    <label>File #1:</label>
    <input type="file" name="file1" multiple>
    <br>
    <label>File #2:</label>
    <input type="file" name="file2" multiple>
    <br>
    <label>File #3:</label>
    <input type="file" name="file3" multiple>
    <br>
    <label>File #4:</label>
    <input type="file" name="file4" multiple>
    <br>
    <label>File #5:</label>
    <input type="file" name="file5" multiple>
    <br>
    <input type="submit" value="Upload">
    <input type="reset" value="Reset">
</form>
</body>
</html>
