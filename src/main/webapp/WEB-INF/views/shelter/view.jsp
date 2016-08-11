<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Shelter Info</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Shelter View</h1>
    <p>This page displays detailed information about a specific shelter.</p>
    <lp:userTag user="${shelter}"  baseUrl="${pageContext.request.contextPath}" viewer="${userInfo.user}" detailed="true" />

    <h2>Listings</h2>
    <c:choose>
        <c:when test="${empty listings}">
            <div class="warning">No listings available.</div>
        </c:when>
        <c:otherwise>
            <c:forEach var="listing" items="${listings}">
                <lp:listingTag baseUrl="${pageContext.request.contextPath}" listing="${listing}" detailed="false" />
            </c:forEach>
        </c:otherwise>
    </c:choose>
</body>
</html>
