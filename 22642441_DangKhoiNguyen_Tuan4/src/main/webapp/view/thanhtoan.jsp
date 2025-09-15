<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/15/2025
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thanh toán</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5" style="max-width: 600px;">
    <h2 class="text-center mb-4">Thanh toán</h2>

    <form action="${pageContext.request.contextPath}/checkout" method="post">
        <div class="mb-3">
            <label for="fullname" class="form-label">Họ tên</label>
            <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Nhập họ tên" required>
        </div>

        <div class="mb-3">
            <label for="address" class="form-label">Địa chỉ giao hàng</label>
            <input type="text" class="form-control" id="address" name="address" placeholder="Nhập địa chỉ" required>
        </div>

        <div class="mb-3">
            <label for="total" class="form-label">Tổng tiền</label>
            <input type="text" class="form-control" id="total" name="total"
                   value="${sessionScope.cart != null ? sessionScope.cart.totalPrice : 0}" readonly>

        </div>

        <div class="mb-3">
            <label class="form-label">Phương thức thanh toán</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="paymentMethod" id="paypal" value="Paypal" checked>
                <label class="form-check-label" for="paypal">Paypal</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="paymentMethod" id="atm" value="ATM Debit">
                <label class="form-check-label" for="atm">ATM Debit</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="paymentMethod" id="visa" value="Visa/Master card">
                <label class="form-check-label" for="visa">Visa/Master card</label>
            </div>
        </div>

        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-success">Thanh toán</button>
            <a href="${pageContext.request.contextPath}/cart" class="btn btn-secondary">Hủy</a>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

