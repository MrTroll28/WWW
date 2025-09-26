<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/26/2025
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="products">Xem danh sach</a>
<h1>Gio hang</h1>
<table border="1" width="80%">
    <tr>
        <th>Ma SP</th>
        <th>Ten SP</th>
        <th>Gia</th>
        <th>Mo ta</th>
        <th>Quantity</th>
        <th>Total Price</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${cart.items}" var="item">
        <tr>
            <td>${item.product.id}</td>
            <td>${item.product.model}</td>
            <td>${item.product.price}</td>
            <td>${item.product.description}</td>
            <td>${item.quantity}</td>
            <td>${item.total}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
