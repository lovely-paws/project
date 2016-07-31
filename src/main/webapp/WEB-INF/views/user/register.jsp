<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Register as User</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Register</h1>
    <spring:form action="create" method="POST" modelAttribute="createUserRequest">
        <table>
            <tr>
                <td><spring:label path="username">Username</spring:label></td>
                <td><spring:input path="username" /></td>
            </tr>
            <tr>
                <td><spring:label path="passwordSha512">Password</spring:label></td>
                <td><spring:password path="passwordSha512" /></td>
            </tr>
            <tr>
                <td><spring:label path="name">Name</spring:label></td>
                <td><spring:input path="name" /></td>
            </tr>
            <tr>
                <td><spring:label path="email">E-Mail Address</spring:label></td>
                <td><spring:input path="email" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Register" /></td>
            </tr>
        </table>
    </spring:form>
</body>
</html>
