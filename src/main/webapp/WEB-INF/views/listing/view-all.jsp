<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Home</a>
<h1>Listings</h1>
<form action="" method="GET">
    <ul>
        <li><label>Shelter</label><input type="text" name="shelterId" /></li>
        <li><label>Animal Type</label><input type="text" name="animalTypeId" /></li>
        <li><label>Color</label><input type="text" name="color" /></li>
        <li><input type="submit" /></li>
    </ul>
</form>
<ul>
<% if(request.getAttribute("listings") != null) {
    List<Listing> listings = (List<Listing>)request.getAttribute("listings");
    for(Listing listing : listings) {
        request.setAttribute("listing", listing);
%>
<li><a href="${pageContext.request.contextPath}/listing/view/${listing.id}">${listing.id}</a>: ${listing.name} @ ${listing.shelter.name}</li>
<% }
}%>
</ul>

</body>
</html>
