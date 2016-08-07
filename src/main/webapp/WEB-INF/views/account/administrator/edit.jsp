<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Edit Account</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Edit Account</h1>
    <spring:form cssClass="search-form" action="${pageContext.request.contextPath}/account/edit.do" modelAttribute="accountInfo">
        <table>
            <tr>
                <td><spring:label path="id">ID</spring:label></td>
                <td><spring:hidden path="id" />${accountInfo.id}</td>
            </tr>
            <tr>
                <td><spring:label path="username">Name</spring:label></td>
                <td><spring:hidden path="username" />${accountInfo.username}</td>
            </tr>
            <tr>
                <td><spring:label path="password">Password (Leave blank to keep current)</spring:label></td>
                <td><spring:password path="password" /></td>
            </tr>
            <tr>
                <td><spring:label path="emailAddress">E-Mail Address</spring:label></td>
                <td><spring:input path="emailAddress" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Update" /></td>
            </tr>
        </table>
    </spring:form>
</body>
</html>
