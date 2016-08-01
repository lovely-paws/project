<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.AnimalType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Browse Listings</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Listings</h1>
    <spring:form action="#" method="get" modelAttribute="listingSearch" cssClass="search-form">
        <spring:label path="animalTypeId" cssClass="search-label">Animal Type:</spring:label>
        <spring:select path="animalTypeId" cssClass="search-select">
            <spring:option value="">Any Type</spring:option>
            <spring:options items="${animalTypes}" itemValue="id" itemLabel="name" cssClass="search-option" />
        </spring:select>
        <spring:label path="shelterId" cssClass="search-label">Shelter:</spring:label>
        <spring:select path="shelterId" cssClass="search-select">
            <spring:option value="">Any Shelter</spring:option>
            <spring:options items="${shelters}" itemValue="id" itemLabel="name" cssClass="search-option" />
        </spring:select>
        <input type="submit" class="search-submit" value="Search" />
    </spring:form>
    
    <% if(request.getAttribute("listings") != null) {
        List<Listing> listings = (List<Listing>)request.getAttribute("listings");
        for(Listing listing : listings) {
            request.setAttribute("listing", listing);
            if(!listing.isVisible()) { continue; }
    %>
        <lp:listingTag listing="${listing}" baseUrl="${pageContext.request.contextPath}" detailed="false" />
    <% }
    } else { %>
<p>No matches found!</p>
    <% } %>

</body>
</html>
