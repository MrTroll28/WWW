<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/15/2025
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Chi tiết sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container my-3" style="max-width: 70%;">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <form class="d-flex" action="products" method="get">
            <input class="form-control form-control-sm me-2" style="width:300px;"  type="search" name="keyword" placeholder="Tìm sách hoặc tác giả..." value="${param.keyword}">
            <button class="btn btn-sm btn-primary" type="submit">🔍 Tìm</button>
        </form>

        <a href="${pageContext.request.contextPath}/cart" class="btn btn-outline-secondary">
            🛒 Xem giỏ hàng
        </a>
    </div>
</div>
<div class="container my-5" style="max-width: 70%;">
    <div class="row">
        <!-- Ảnh sách -->
        <div class="col-md-5">
            <img src="images/${product.imageUrl}.png" class="img-fluid rounded shadow-sm" alt="${product.name}">
        </div>

        <!-- Thông tin sách -->
        <div class="col-md-7">
            <h2 class="mb-3">${product.name}</h2>
            <p class="text-danger fw-bold fs-5">
                <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₫"/>
            </p>
            <p class="text-muted">Tồn kho: ${product.quantity}</p

            <form action="${pageContext.request.contextPath}/cart" method="post" class="mt-4">
                <input type="hidden" name="id" value="${product.id}">
                <input type="hidden" name="action" value="add">
                <div class="mb-3">
                    <label for="quantity" class="form-label">Số lượng:</label>
                    <input type="number" id="quantity" name="quantity" value="1" min="1" max="${product.quantity}" class="form-control w-25">
                </div>
                <button type="submit" class="btn btn-success btn-lg">+ Thêm vào giỏ</button>
            </form>
            <div>
                <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary btn-lg mt-3">← Quay lại danh sách</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>

