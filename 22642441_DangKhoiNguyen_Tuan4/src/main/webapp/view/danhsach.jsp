<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách sách</title>
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

<div class="container my-4" style="max-width: 70%;">
    <div class="row">
        <c:forEach var="book" items="${products}">
            <div class="col-md-3 mb-4">
                <div class="card h-100 shadow-sm">
                    <img src="images/${book.imageUrl}.png" class="card-img-top" alt="${book.name}" style="height:350px; object-fit:cover;">
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title" style="font-size: 16px; min-height:40px;">${book.name}</h5>
                        <p class="text-danger fw-bold mb-1">
                            <fmt:formatNumber value="${book.price}" type="currency" currencySymbol="₫"/>
                        </p>
                        <p class="text-muted mb-3">Tồn kho: ${book.quantity}</p>

                        <div class="mt-auto d-flex flex-column gap-2">
                            <form action="${pageContext.request.contextPath}/cart" method="post">
                                <input type="hidden" name="id" value="${book.id}">
                                <input type="hidden" name="action" value="add">
                                <button type="submit" class="btn btn-success w-100">+ Thêm vào giỏ</button>
                            </form>
                            <a href="${pageContext.request.contextPath}/product?id=${book.id}" class="btn btn-primary w-100">👁 Xem chi tiết</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
