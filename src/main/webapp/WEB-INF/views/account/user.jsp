<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: My Account</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <lp:userTag user="${userInfo.user}" />
    <h1>Actions for End Users</h1>
    <ul>
        <li>Browse Shelters</li>
        <li>Browse Listings</li>
        <li><a href="${pageContext.request.contextPath}/cart">My Cart</a></li>
    </ul>
</body>
</html>
