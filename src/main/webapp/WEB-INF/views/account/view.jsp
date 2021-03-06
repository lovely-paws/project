<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: My Account</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.less" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <p><a href="${pageContext.request.contextPath}/account/edit">Edit My Information</a></p>
        <lp:userTag user="${userInfo.user}" baseUrl="${pageContext.request.contextPath}" />
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>
