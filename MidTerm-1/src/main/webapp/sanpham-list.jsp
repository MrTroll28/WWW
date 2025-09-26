<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/26/2025
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="cart">Xem gio hang</a>
<h1>Danh sach mat hang</h1>
<table border="1" width="80%">
    <tr>
        <th>Ma SP</th>
        <th>Ten SP</th>
        <th>Gia</th>
        <th>Mo ta</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${productList}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.model}</td>
            <td>${p.price}</td>
            <td>${p.description}</td>
            <td>
                <a href="products?id=${p.id}">Xem chi tiet</a>
                <form action="cart?action=ADD&id=${p.id}" method="post">
                    <button>Them vao gio hang</button>
                </form>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
