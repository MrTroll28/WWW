<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 8/25/2025
  Time: 7:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mail</title>
</head>
<body>
<h1>Send Mail</h1>
<form action="sendMail" method="post" enctype="multipart/form-data">
    <label>Người nhận:</label>
    <input type="email" name="to" required>
    <br>
    <label>Tiêu đề:</label>
    <input type="text" name="subject" required>
    <br>
    <label>Nội dung:</label>
    <textarea name="message" rows="4" cols="50" required></textarea>
    <br>
    <label>File đính kèm:</label>
    <input type="file" name="attachment" accept=".jpg,.jpeg,.png,.pdf,.docx">
    <br>
    <button type="submit">Gửi Mail</button>
</form>
</body>
</html>
