<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Detail</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        .product-detail {
            max-width: 600px;
            margin: auto;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px #ddd;
        }
        img {
            max-width: 100%;
            border-radius: 8px;
        }
        .price {
            color: darkred;
            font-size: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="product-detail">
    <h2>${product.model}</h2>
    <img src="images/${product.imageUrl}" alt="${product.model}"/><br/><br/>
    <p><b>Description:</b> ${product.description}</p>
    <p><b>Quantity:</b> ${product.quantity}</p>
    <p class="price">
        Price: <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$"/>
    </p>

    <form action="${pageContext.request.contextPath}/cart" method="post">
        <input type="number" name="quantity" value="1" min="1" style="width: 60px"/> <br/><br/>
        <input type="hidden" name="id" value="${product.id}">
        <input type="hidden" name="price" value="${product.price}">
        <input type="hidden" name="model" value="${product.model}">
        <input type="hidden" name="action" value="add">
        <button type="submit">Add to Cart</button>
    </form>

    <br/>
    <a href="${pageContext.request.contextPath}/products">Back to Product List</a>
</div>
</body>
</html>
