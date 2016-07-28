<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="cart" class="edu.johnshopkins.lovelypaws.beans.Cart" scope="session" />
<ul>
<% for(Long id : cart.getIds()) { %>
    <li><%= id %></li>
<% } %>

    <% if(cart.getIds().size() > 0) { %>
        <a href="${pageContext.request.contextPath}/cart/checkout">Checkout</a>
    <% } %>
</ul>
</body>
</html>
