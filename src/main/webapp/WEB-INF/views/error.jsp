<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>Lovely Paws: Error</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Uh Oh :(</h1>
    <p>
        If you've reached this page, it means you've entered in an invalid URL or provided
        some input which the server could not process. A record of this error has been
        provided to the system administrators.
    </p>
</body>
</html>
