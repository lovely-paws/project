<%@ page import="edu.johnshopkins.lovelypaws.beans.Cart" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: View Cart</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>My Cart</h1>
    <% List<Listing> listings = (List<Listing>)request.getAttribute("listings"); %>
    <% if(listings.size() == 0) { %>
        <p>Your cart is empty!</p>
    <% } else { %>
    <table>
        <tr>
            <td>Name</td>
            <td>Type</td>
            <td>Actions</td>
        </tr>
    <%
        for(Listing listing : listings) {
            request.setAttribute("listing", listing); %>
        <tr>
            <td><a href="${pageContext.request.contextPath}/listing/view/${listing.id}">${listing.name}</a></td>
            <td>${listing.animalType.name}</td>
            <td><a href="${pageContext.request.contextPath}/cart/remove/${listing.id}">Remove from Cart</a></td>
        </tr>
    <%
        }
     %>
    <p>
        <a href="${pageContext.request.contextPath}/cart/checkout">Checkout</a>
    </p>
<% } %>
</body>
</html>
