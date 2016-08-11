<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Browse Users</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <h1>Users</h1>
        <c:forEach var="user" items="${users}">
            <lp:userTag baseUrl="${pageContext.request.contextPath}" viewer="${userInfo.user}" user="${user}" />
        </c:forEach>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>