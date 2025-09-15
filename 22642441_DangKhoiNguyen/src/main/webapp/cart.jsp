<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Shopping Cart</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background:#f7f7f7; }
        h2 { text-align:center; color:#333; }
        .wrap { max-width:1000px; margin: 0 auto; background:#fff; padding:20px; border-radius:8px; box-shadow:0 2px 8px rgba(0,0,0,0.06); }
        table { width:100%; border-collapse:collapse; margin-top:10px; }
        th, td { padding:12px; border-bottom:1px solid #eee; text-align:left; vertical-align:middle; }
        th { background:#fafafa; color:#333; font-weight:600; }
        .price { color:#e63946; font-weight:bold; }
        .qty { width:80px; text-align:center; }
        .actions { text-align:center; }
        .btn { padding:8px 12px; border-radius:6px; border:none; cursor:pointer; }
        .btn-red { background:#d64545; color:#fff; }
        .btn-primary { background:#2d8cff; color:#fff; }
        .total-row { text-align:right; font-size:18px; font-weight:700; padding-top:12px; }
        .empty { text-align:center; padding:40px 0; color:#666; }
        img.thumb { width:80px; height:80px; object-fit:cover; border-radius:6px; }
        .product-name { font-weight:600; color:#222; }
        .small { color:#777; font-size:13px; }
    </style>
</head>
<body>
<div class="wrap">
    <h2>Your Shopping Cart</h2>

    <!-- Kiểm tra đúng scope: cart tồn tại trong session (session scope) -->
    <c:if test="${empty sessionScope.cart or empty sessionScope.cart.items}">
        <div class="empty">
            Your cart is empty. <a href="${pageContext.request.contextPath}/products">Go shopping</a>
        </div>
    </c:if>

    <c:if test="${not empty sessionScope.cart and not empty sessionScope.cart.items}">
        <table>
            <thead>
            <tr>
                <th style="width:50%;">Product</th>
                <th style="width:12%;">Price</th>
                <th style="width:12%;">Quantity</th>
                <th style="width:14%;">Subtotal</th>
                <th style="width:12%;" class="actions">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="total" value="0" scope="page"/>
            <c:forEach var="item" items="${sessionScope.cart.items}">
                <tr>
                    <td>
                        <div style="display:flex; gap:12px; align-items:center;">
                            <img class="thumb" src="${pageContext.request.contextPath}/images/${item.product.imageUrl}" alt="${item.product.model}" />
                            <div>
                                <div class="product-name">${item.product.model}</div>
                                <div class="small">ID: ${item.product.id}</div>
                            </div>
                        </div>
                    </td>
                    <td class="price">
                        <fmt:formatNumber value="${item.product.price}" type="number" minFractionDigits="0" />
                    </td>
                    <td class="qty">${item.quantity}</td>
                    <td>
                        <fmt:formatNumber value="${item.product.price * item.quantity}" type="number" minFractionDigits="0" />
                    </td>
                    <td class="actions">
                        <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="remove"/>
                            <input type="hidden" name="id" value="${item.product.id}"/>
                            <button type="submit" class="btn btn-red">Remove</button>
                        </form>
                    </td>
                </tr>
                <c:set var="total" value="${total + (item.product.price * item.quantity)}" scope="page"/>
            </c:forEach>
            </tbody>
        </table>

        <div class="total-row">
            Total:
            <fmt:formatNumber value="${total}" type="number" minFractionDigits="0" />
        </div>

        <div style="text-align:center; margin-top:18px;">
            <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                <input type="hidden" name="action" value="clear"/>
                <button type="submit" class="btn">Clear Cart</button>
            </form>
            &nbsp;&nbsp;
            <button class="btn btn-primary">Checkout</button>
        </div>
    </c:if>
</div>
</body>
</html>
