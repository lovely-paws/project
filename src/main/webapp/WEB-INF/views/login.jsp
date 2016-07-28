<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />

    <a href="${pageContext.request.contextPath}/">Home</a>
        <form action="login" method="POST">
            <div>
                <label>Username</label>
                <input type="text" name="username" />
            </div>
            <div>
                <label>Password</label>
                <input type="password" name="password" />
            </div>
            <input type="submit" />
        </form>
    </body>
</html>
