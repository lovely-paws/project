<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.AdoptionRequest" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Review Requests</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Pending Requests</h1>
    <p>
        Below you will find the pending adoption requests associated with your listings. Note that once you
        accept the first request associated with a listing, all other listings will automatically be closed.
    </p>
    <c:choose>
        <c:when test="${empty requests}">
            <div class="warning">No requests pending!</div>
        </c:when>
        <c:otherwise>
            <c:forEach var="request" items="${requests}">
                <lp:adoptionRequestTag adoptionRequest="${request}" baseUrl="${pageContext.request.contextPath}" />
            </c:forEach>
        </c:otherwise>
    </c:choose>
</body>
</html>
