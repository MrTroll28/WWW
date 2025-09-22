<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/22/2025
  Time: 7:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Form danh mục</title>
</head>
<body>
<h2>${danhMuc != null ? "Chỉnh sửa danh mục" : "Thêm danh mục"}</h2>

<form action="danhmuc" method="post">
  <input type="hidden" name="madm" value="${danhMuc.madm}"/>
  <label>Tên danh mục: </label>
  <input type="text" name="tendanhmuc" value="${danhMuc.tendanhmuc}"/><br/><br/>
  <label>Nguoi quan ly; </label>
    <input type="text" name="nguoiquanly" value="${danhMuc.nguoiquanly}"/><br/><br/>
  <label>Ghi chu:</label>
    <input type="text" name="ghichu" value="${danhMuc.ghichu}"/><br/><br/>
  <input type="submit" value="Lưu"/>
</form>

<a href="danhmuc">Quay lại danh sách</a>
</body>
</html>

