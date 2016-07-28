<%@ page import="static edu.johnshopkins.lovelypaws.LovelyPawsConstants.ATTRIBUTE_USER_ID" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws Pet Adoption Site</title>
</head>
<body>
<jsp:useBean id="userInfo" class="edu.johnshopkins.lovelypaws.beans.UserInfo" scope="session" />
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <a href="${pageContext.request.contextPath}/">Home</a>

    <% if(userInfo.getUser() == null) { %>
        <h1>You are not logged in.</h1>
    <% } else { %>
        <h1>You are logged in.</h1>
    <% } %>
</body>
</html>
