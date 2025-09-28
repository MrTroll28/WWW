<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/28/2025
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AccountForm</title>
</head>
<body>
<a href="account">Xem danh sach tai khoan</a> <br>
<a href="account?action=CREATE">Them tai khoan</a>
<h1>Tao moi tai khoan</h1>

<form action="account" method="post">
    <div>
        <label>Owner name</label>
        <input name="ownerName" required>
    </div>

    <div>
        <label>Card number</label>
        <input name="cardNumber" required>
    </div>

    <div>
        <label>Owner address</label>
        <input name="ownerAddress" required>
    </div>

    <div>
        <label>Amount</label>
        <input name="amount" required>
    </div>

    <button type="submit">Luu</button>
</form>
</body>
</html>
