<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/22/2025
  Time: 7:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Danh sách tin tức</title>
</head>
<body>
<h2>Danh sách tin tức</h2>
<a href="tintuc?action=new">Thêm tin tức</a>
<table border="1" cellpadding="5" cellspacing="0">
  <tr>
    <th>ID</th>
    <th>Tiêu đề</th>
    <th>Nội dung</th>
    <th>ID Danh mục</th>
    <th>Hành động</th>
  </tr>
  <c:forEach var="tt" items="${tinList}">
    <tr>
      <td>${tt.matt}</td>
      <td>${tt.tieude}</td>
      <td>${tt.noidungtt}</td>
      <td>${tt.madm}</td>
      <td>
        <a href="tintuc?action=edit&id=${tt.matt}">Sửa</a> |
        <a href="tintuc?action=delete&id=${tt.matt}" onclick="return confirm('Xóa tin tức này?')">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

