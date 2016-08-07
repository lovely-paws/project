<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Shelter Info</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <lp:userTag user="${shelter}"  baseUrl="${pageContext.request.contextPath}" viewer="${userInfo.user}"/>
    <h2>Listings</h2>
    <ul>
        <% for(Listing listing : ((Shelter)(request.getAttribute("shelter"))).getListings()) {
            request.setAttribute("listing", listing);
        %>
            <li>
                <lp:listingTag baseUrl="${pageContext.request.contextPath}" listing="${listing}" detailed="false" />
            </li>
        <% } %>
    </ul>
</body>
</html>
