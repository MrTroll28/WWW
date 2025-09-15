<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/15/2025
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <div class="flex justify-content-between align-items-center mb-4">
        <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary btn-lg mt-3">← Quay lại danh sách</a>
        <h2 class="text-center mb-4">Giỏ hàng của bạn</h2>
    </div>

    <c:if test="${empty sessionScope.cart or empty sessionScope.cart.items}">
        <div class="alert alert-info text-center">
            Giỏ hàng trống. <a href="${pageContext.request.contextPath}/products" class="alert-link">Quay lại mua sắm</a>
        </div>
    </c:if>

    <!-- Giỏ hàng có sản phẩm -->
    <c:if test="${not empty sessionScope.cart and not empty sessionScope.cart.items}">
        <div class="table-responsive">
            <table class="table table-bordered align-middle">
                <thead class="table-light">
                <tr>
                    <th>Sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Tổng</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="total" value="0" scope="page"/>
                <c:forEach var="item" items="${sessionScope.cart.items}">
                    <tr>
                        <td>
                            <div class="d-flex align-items-center gap-2">
                                <img src="images/${item.product.imageUrl}.png" class="img-thumbnail" style="width:80px; height:80px; object-fit:cover;">
                                <div>
                                    <div class="fw-bold">${item.product.name}</div>
                                    <div class="text-muted small">ID: ${item.product.id}</div>
                                </div>
                            </div>
                        </td>
                        <td><fmt:formatNumber value="${item.product.price}" type="currency" currencySymbol="₫"/></td>
                        <td>${item.quantity}</td>
                        <td><fmt:formatNumber value="${item.product.price * item.quantity}" type="currency" currencySymbol="₫"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/cart" method="post">
                                <input type="hidden" name="action" value="remove"/>
                                <input type="hidden" name="id" value="${item.product.id}"/>
                                <button type="submit" class="btn btn-sm btn-danger">Xóa</button>
                            </form>
                        </td>
                    </tr>
                    <c:set var="total" value="${sessionScope.cart.totalPrice}" scope="page"/>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="d-flex justify-content-between align-items-center mt-3">
            <div class="fs-5 fw-bold">
                Tổng tiền: <fmt:formatNumber value="${total}" type="currency" currencySymbol="₫"/>
            </div>
            <div>
                <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="clear"/>
                    <button type="submit" class="btn btn-warning">Xóa tất cả</button>
                </form>
                <a href="view/thanhtoan.jsp" class="btn btn-primary ms-2">Thanh toán</a>
            </div>
        </div>
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
