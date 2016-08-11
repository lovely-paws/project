<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Edit Animal Type</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.less" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <h1>Create an Animal Type</h1>
        <spring:form action="${pageContext.request.contextPath}/animal-type/edit.do" modelAttribute="animalTypeInfo">
            <table>
                <tr>
                    <td><spring:label path="id">ID</spring:label></td>
                    <td><spring:hidden path="id" />${animalTypeInfo.id}</td>
                </tr>
                <tr>
                    <td><spring:label path="name">Name</spring:label></td>
                    <td><spring:input path="name" /></td>
                </tr>
                <tr>
                    <td><spring:label path="description">Description</spring:label></td>
                    <td><spring:textarea path="description" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Update" /></td>
                </tr>
            </table>
        </spring:form>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>
