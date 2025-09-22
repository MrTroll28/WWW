<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/22/2025
  Time: 7:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Danh sách danh mục</title>
</head>
<body>
<h2>Danh sách danh mục</h2>
<a href="danhmuc?action=new">Thêm danh mục</a>
<table border="1" cellpadding="5" cellspacing="0">
  <tr>
    <th>ID</th>
    <th>Tên danh mục</th>
    <th>Hành động</th>
  </tr>
  <c:forEach var="dm" items="${danhmucList}">
    <tr>
      <td>${dm.madm}</td>
      <td>${dm.tendanhmuc}</td>
      <td>
        <a href="danhmuc?action=edit&id=${dm.madm}">Sửa</a> |
        <a href="danhmuc?action=delete&id=${dm.madm}" onclick="return confirm('Xóa danh mục này?')">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

