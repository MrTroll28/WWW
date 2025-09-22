<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/22/2025
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="iuh.fit.kn.model.Department" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Employee Information</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%">
  <h2>Employee Information</h2>

  <form action="${pageContext.request.contextPath}/employees" method="post">
    <input type="hidden" name="id"/>

    <div class="mb-3">
      <label for="name" class="form-label">Name:</label>
      <input type="text" id="name" name="name" class="form-control"/>
    </div>

    <div class="mb-3">
      <label for="salary" class="form-label">Salary:</label>
      <input type="text" id="salary" name="salary" class="form-control"/>
    </div>

    <div class="mb-3">
      <label for="departmentId" class="form-label">Department:</label>
      <select id="departmentId" name="departmentId" class="form-select">
        <c:forEach var="dep" items="${departments}">
          <option value="${dep.id}">${dep.name}</option>
        </c:forEach>
      </select>
    </div>

    <input type="submit" value="Save" class="btn btn-primary"/>
  </form>
</div>
</body>
</html>
