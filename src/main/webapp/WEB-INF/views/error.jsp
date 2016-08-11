<%@ page import="org.slf4j.LoggerFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <title>Lovely Paws: Error</title>
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <h1>Uh Oh :(</h1>
        <p>
            If you've reached this page, it means you've entered in an invalid URL or provided
            some input which the server could not process. A record of this error has been
            provided to the system administrators.
        </p>
        <p>
            Status Code <%= request.getAttribute("javax.servlet.error.status_code") %>
            caused by <%= request.getAttribute("javax.servlet.error.exception_type") %>
        </p>
        <%
            LoggerFactory.getLogger("ERROR PAGE").warn("An unhandled exception was reported back to the user.",
                    request.getAttribute("javax.servlet.error.exception"));
        %>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>
