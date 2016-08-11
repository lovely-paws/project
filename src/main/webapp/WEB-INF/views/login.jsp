<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Log In</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.less" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <h1>Login</h1>
        <p>Please log in using the form below.</p>
        <spring:form action="login" method="POST" modelAttribute="loginData">
            <table>
                <tr>
                    <td>Username</td>
                    <td><spring:input path="username" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><spring:password path="password" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Login" /></td>
                </tr>
            </table>
        </spring:form>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>
