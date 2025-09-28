<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/26/2025
  Time: 7:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Thuoc</title>
</head>
<body>
<a href="loaiThuoc">Danh sach cac loai thuoc</a> <br>
<a href="thuoc">Danh sach thuoc</a> <br>
<a href="thuoc?action=CREATE">Them moi thuoc</a> <br>
<h1>Danh sach loai thuoc</h1>

<form action="thuoc" method="get">
    <select name="loaiThuocId">
        <option value="All">--Tat ca--</option>
        <c:forEach items="${loaiThuocList}" var="loaiThuoc">
            <option value="${loaiThuoc.maLoai}">${loaiThuoc.tenLoai}</option>
        </c:forEach>
    </select>
        <button type="submit">Tim kiem</button>
</form>

<table border="1" width="80%">
    <tr>
        <th>ID</th>
        <th>Ten thuoc</th>
        <th>Gia</th>
        <th>Nam san xuat</th>
        <th>Loai thuoc</th>
    </tr>
    <c:forEach items="${thuocList}" var="thuoc">
        <tr>
            <td>${thuoc.maThuoc}</td>
            <td>${thuoc.tenThuoc}</td>
            <td>${thuoc.gia}</td>
            <td>${thuoc.namSX}</td>
            <td>${thuoc.loaiThuoc.tenLoai}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
