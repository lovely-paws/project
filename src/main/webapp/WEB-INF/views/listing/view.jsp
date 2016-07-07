<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Home</a>
<%= request.getAttribute("message") %>
<h1>Listing #${listing.id}: ${listing.name}</h1>
<p>${listing.description}</p>
<table>
    <tr>
        <td>Animal Type</td>
        <td>${listing.animalType.name}</td>
    </tr>
    <tr>
        <td>Color</td>
        <td>${listing.color}</td>
    </tr>
</table>

<h2>Contact</h2>
<p>${listing.shelter.name}</p>
<p>${listing.shelter.address.line1}</p>
<p>${listing.shelter.address.line2}</p>
<p>${listing.shelter.address.city}, ${listing.shelter.address.state} ${listing.shelter.address.zip}</p>
<p>${listing.shelter.phoneNumber}</p>

<% if(request.getAttribute("canAdopt") != null) { %>
    <a href="${pageContext.request.contextPath}/cart/add?id=${listing.id}">Add to Cart</a>
<% } %>
</body>
</html>
