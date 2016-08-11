<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:choose>
        <c:when test="${empty listings}">
            <div class="warning">Your cart is empty!</div>
        </c:when>
        <c:otherwise>
            <table class="cart">
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="listing" items="${listings}">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${empty listing.imageFile}">
                                        None!
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/listing/image/${listing.id}" class="cart-image" />
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><a href="${pageContext.request.contextPath}/listing/view/${listing.id}">${listing.name}</a></td>
                            <td>${listing.animalType.name}</td>
                            <td><a href="${pageContext.request.contextPath}/cart/remove/${listing.id}">Remove from Cart</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p>
                <a href="${pageContext.request.contextPath}/cart/checkout">Checkout</a>
            </p>
        </c:otherwise>
    </c:choose>
</body>
</html>
