<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/22/2025
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Department Information</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%">
    <h2>Department Information</h2>

    <form action="${pageContext.request.contextPath}/departments" method="post">
        <input type="hidden" name="id" value="${department.id}"/>

        <div class="mb-3">
            <label for="name" class="form-label">Department Name:</label>
            <input type="text" id="name" name="name" class="form-control" value="${department.name}"/>
        </div>

        <input type="submit" value="Save" class="btn btn-primary"/>
    </form>
</div>
</body>
</html>
