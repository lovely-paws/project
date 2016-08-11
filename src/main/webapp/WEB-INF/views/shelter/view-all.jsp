<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: View Shelters</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.less" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <h1>Browse Shelters</h1>
        <p>The following shelters participate in the Lovely Paws network.</p>
        <c:choose>
            <c:when test="${empty shelters}">
                <div class="warning">No shelters have registered with the network.</div>
            </c:when>
            <c:otherwise>
                <c:forEach var="shelter" items="${shelters}">
                    <lp:userTag user="${shelter}" viewer="${userInfo.user}" baseUrl="${pageContext.request.contextPath}" />
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>
