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
    <title>AccountList</title>
</head>
<body>
<a href="account">Xem danh sach tai khoan</a> <br>
<a href="account?action=CREATE">Them tai khoan</a>
<h1>Danh sach tai khoan</h1>

<form action="account" method="get">
    <input type="hidden" name="action" value="SEARCH">

    <select name="tieuChi">
        <option value="AMOUNT">Amount</option>
        <option value="ADDRESS">Address</option>
    </select>

    <div>
        <h3>Tim kiem bang amount</h3>
        <div>
            <label>Min</label>
            <input name="min">
        </div>
        <div>
            <label>Max</label>
            <input name="max">
        </div>
    </div>

    <div>
        <h3>Tim kiem bang address</h3>
        <label>Address</label>
        <input name="address">
    </div>

    <button type="submit">Tim</button>
</form>

<table width="80%" border="1">
    <tr>
        <th>ID</th>
        <th>Owner name</th>
        <th>Card number</th>
        <th>Owner address</th>
        <th>Amount</th>
    </tr>

    <c:forEach items="${accountList}" var="acc">
        <tr>
            <td>${acc.accountNumber}</td>
            <td>${acc.ownerName}</td>
            <td>${acc.cardNumber}</td>
            <td>${acc.ownerAddress}</td>
            <td>${acc.amount}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
