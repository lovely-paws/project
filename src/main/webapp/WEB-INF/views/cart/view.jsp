<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />
<jsp:useBean id="cart" class="edu.johnshopkins.lovelypaws.beans.Cart" scope="session" />
<h1>My Cart</h1>
<% if(cart.getIds().size() == 0) { %>
    <p>Your cart is empty!</p>
<% } else { %>
    <ul>
    <% for(Long id : cart.getIds()) { %>
        <li><%= id %></li>
    <% } %>
    </ul>
    <p>
        <a href="${pageContext.request.contextPath}/cart/checkout">Checkout</a>
    </p>
<% } %>
</body>
</html>
