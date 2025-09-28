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
<h1>Them moi thuoc</h1>

<form action="thuoc" method="post">

    <div>
        <label>Ten thuoc:</label>
        <input type="text" id="tenThuoc" name="tenThuoc" required>
    </div>

    <div>
        <label>Gia:</label>
        <input name="gia" required type="number">
    </div>

    <div>
        <label>Nam san xuat:</label>
        <input name="namSX" required type="number">
    </div>

    <select name="loaiThuocId">
        <c:forEach items="${loaiThuocList}" var="loaiThuoc">
            <option value="${loaiThuoc.maLoai}">${loaiThuoc.tenLoai}</option>
        </c:forEach>
    </select>
        <button type="submit">Tao moi</button>
</form>

</body>
</html>
