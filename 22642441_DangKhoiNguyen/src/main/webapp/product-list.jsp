<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background: #f9f9f9;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }
        .product-card {
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.15);
            padding: 15px;
            text-align: center;
            transition: transform 0.2s;
        }
        .product-card:hover {
            transform: scale(1.03);
        }
        .product-card img {
            max-width: 100%;
            max-height: 150px;
            border-radius: 8px;
            margin-bottom: 10px;
        }
        .product-card b {
            font-size: 16px;
            color: #222;
        }
        .price {
            color: #e63946;
            font-weight: bold;
            margin: 8px 0;
            display: block;
        }
        .btn {
            background: #4CAF50;
            color: white;
            padding: 6px 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            margin-top: 5px;
        }
        .btn:hover {
            background: #45a049;
        }
        .link {
            display: inline-block;
            margin-top: 8px;
            color: #007bff;
            text-decoration: none;
            font-size: 14px;
        }
        .link:hover {
            text-decoration: underline;
        }
        .top-links {
            display: flex;
            justify-content: end;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="top-links">
    <p><a href="cart" class="link">🛒 View Cart</a></p>
</div>

<h1>Product List</h1>

<div class="container">
    <c:forEach items="${products}" var="p">
        <div class="product-card">
            <b>${p.model}</b><br/>
            <img src="images/${p.imageUrl}" alt="${p.model}"><br/>
            <span class="price">
                $<fmt:formatNumber value="${p.price}" type="number" minFractionDigits="0" />
            </span>

            <form action="${pageContext.request.contextPath}/cart" method="post">
                <input type="hidden" name="id" value="${p.id}">
                <input type="hidden" name="action" value="add">
                <button type="submit">Add to Cart</button>
            </form>

            <a href="${pageContext.request.contextPath}/product?id=${p.id}" class="link">🔎 Product Detail</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
