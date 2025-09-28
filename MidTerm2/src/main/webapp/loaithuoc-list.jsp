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
    <title>LoaiThuoc</title>
</head>
<body>
<a href="loaiThuoc">Danh sach cac loai thuoc</a> <br>
<a href="thuoc">Danh sach thuoc</a> <br>
<a href="thuoc?action=CREATE">Them moi thuoc</a> <br>
<h1>Danh sach thuoc</h1>

<table border="1" width="80%">
    <tr>
        <th>ID</th>
        <th>Ten loai thuoc</th>
    </tr>
    <c:forEach items="${loaiThuocList}" var="loaiThuoc">
        <tr>
            <td>${loaiThuoc.maLoai}</td>
            <td>${loaiThuoc.tenLoai}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
