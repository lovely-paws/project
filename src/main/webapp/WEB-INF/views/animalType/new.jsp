<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Create an Animal Type</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Create an Animal Type</h1>
    <spring:form cssClass="search-form" modelAttribute="createAnimalTypeRequest">
        <table>
            <tr>
                <td><spring:label path="name">Name</spring:label></td>
                <td><spring:input path="name" /></td>
            </tr>
            <tr>
                <td><spring:label path="description">Description</spring:label></td>
                <td><spring:textarea path="description" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Create" /></td>
            </tr>
        </table>
    </spring:form>
</body>
</html>
