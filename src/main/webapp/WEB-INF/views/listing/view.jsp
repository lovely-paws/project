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
<% if(request.getAttribute("listing") != null) { %>
<h1>Listing #${listing.id}: ${listing.name}</h1>
<p>${listing.description}</p>
<p>${listing.animalType}</p>
<p>${listing.color}</p>
<h2>Contact</h2>
<p>${listing.shelter.name}</p>
<p>${listing.shelter.address.line1}</p>
<p>${listing.shelter.address.line2}</p>
<p>${listing.shelter.address.city}, ${listing.shelter.address.state} ${listing.shelter.address.zip}</p>
<p>${listing.shelter.phoneNumber}</p>
<% } %>
</body>
</html>
