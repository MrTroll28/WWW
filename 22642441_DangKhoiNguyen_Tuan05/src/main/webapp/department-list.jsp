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
    <title>Departments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%">
    <h2>Departments List</h2>

    <a href="${pageContext.request.contextPath}/departments?action=new" class="btn btn-success mb-2">Add Department</a>

    <table class="table table-striped table-hover">
        <tr>
            <th>ID</th>
            <th>Name Department</th>
            <th>Action</th>
        </tr>
        <c:forEach var="dep" items="${departments}">
            <tr>
                <td>${dep.id}</td>
                <td>${dep.name}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/departments?action=edit&id=${dep.id}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/departments?action=delete&id=${dep.id}">Delete</a>
                    <a href="${pageContext.request.contextPath}/employees?action=listByDept&deptId=${dep.id}">View Employees</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="${pageContext.request.contextPath}/employees">Back to Employees</a>
</div>
</body>
</html>

