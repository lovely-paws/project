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
    <h1>Shelter #${shelter.id}: ${shelter.name}</h1>
    <p>${shelter.description}</p>
    <h2>Contact</h2>
    <p>${shelter.address.line1}</p>
    <p>${shelter.address.line2}</p>
    <p>${shelter.address.city}, ${shelter.address.state} ${shelter.address.zip}</p>
    <p>${shelter.phoneNumber}</p>
    <h2>TODO: ANIMAL TYPES</h2>
    <h2>Listings</h2>
    <ul>
        <% for(Listing listing : ((Shelter)(request.getAttribute("shelter"))).getListings()) {
            request.setAttribute("listing", listing);
        %>
            <li>Listing #${listing.id}: ${listing.name}</li>
        <% } %>
    </ul>
</body>
</html>
