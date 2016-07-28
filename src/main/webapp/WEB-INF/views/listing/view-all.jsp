<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.AnimalType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Home</a>
<h1>Listings</h1>
<spring:form action="#" method="GET" modelAttribute="listingSearch">
    <ul>
        <li>
            <label>Shelter</label>
            <select name="shelterId">
                <option value=""></option>
                <% for(Shelter shelter : (List<Shelter>)(request.getAttribute("shelters"))) { %>
                    <option value="<%= shelter.getId() %>"><c:out value="<%= shelter.getName()%>" escapeXml="true" /></option>
                <% } %>
            </select>
        <li>
            <label>Type</label>
            <select name="animalTypeId">
                <option value=""></option>
                <% for(AnimalType animalType : (List<AnimalType>)(request.getAttribute("animalTypes"))) { %>
                    <option value="<%= animalType.getId() %>"><c:out value="<%= animalType.getName()%>" escapeXml="true" /></option>
                <% } %>
        </select>
        </li>
        <li><input type="submit" /></li>
    </ul>
</spring:form>
<ul>
<% if(request.getAttribute("listings") != null) {
    List<Listing> listings = (List<Listing>)request.getAttribute("listings");
    for(Listing listing : listings) {
        if(!listing.isVisible()) { continue; }
        request.setAttribute("listing", listing);
%>
<li><a href="${pageContext.request.contextPath}/listing/view/${listing.id}">${listing.id}</a>: ${listing.name} @ ${listing.shelter.name}</li>
<% }
}%>
</ul>

</body>
</html>
