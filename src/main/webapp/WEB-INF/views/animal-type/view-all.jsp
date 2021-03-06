<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.AnimalType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: View Animal Types</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.less" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <h1>View Animal Types</h1>
        <p>The following animal types may be associated with new listings. You may create or edit these types.</p>
        <p><a href="${pageContext.request.contextPath}/animal-type/create">Create Animal Type</a></p>
        <c:choose>
            <c:when test="${empty animalTypes}">
                <b>No known types!</b>
            </c:when>
            <c:otherwise>
                <c:forEach var="animalType" items="${animalTypes}">
                    <lp:animalTypeTag animalType="${animalType}" baseUrl="${pageContext.request.contextPath}" />
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>
