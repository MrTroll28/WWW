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
  <title>Form tin tức</title>
</head>
<body>
<h2>${tinTuc != null ? "Chỉnh sửa tin tức" : "Thêm tin tức"}</h2>

<form action="tintuc" method="post">
  <input type="hidden" name="matt" value="${tinTuc.matt}"/>
  <label>Tiêu đề: </label>
  <input type="text" name="tieude" value="${tinTuc.tieuDe}"/><br/><br/>

  <label>Nội dung: </label><br/>
  <textarea name="noidung" rows="5" cols="40">${tinTuc.noidungtt}</textarea><br/><br/>

  <label>Danh mục: </label>
    <select name="madm">
        <c:forEach var="dm" items="${danhmucList}">
        <option value="${dm.madm}" ${tinTuc != null && tinTuc.madm == dm.madm ? "selected" : ""}>${dm.tendanhmuc}</option>
        </c:forEach>
    </select><br/>
  <br/>

  <label>Lien ket: </label>
    <input type="text" name="lienket" value="${tinTuc.lienket}"/><br/><br/>

  <input type="submit" value="Lưu"/>
</form>

<a href="tintuc">Quay lại danh sách</a>
</body>
</html>

