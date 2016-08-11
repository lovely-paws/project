<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Edit A Listing</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <spring:form action="${pageContext.request.contextPath}/listing/edit.do" method="POST" modelAttribute="listingInfo">
            <table>
                <tr>
                    <td><spring:label path="id">ID</spring:label></td>
                    <td><spring:hidden path="id"/> ${listingInfo.id}</td>
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
                    <td><spring:label path="animalTypeId">Animal Types</spring:label></td>
                    <td>
                        <spring:select path="animalTypeId">
                            <spring:options itemValue="id" itemLabel="name" items="${animalTypes}" />
                        </spring:select>
                    </td>
                </tr>
                <tr>
                    <td><spring:label path="gender">Gender</spring:label></td>
                    <td>
                        <spring:select path="gender">
                            <spring:options itemValue="name" itemLabel="name" items="${genders}" />
                        </spring:select>
                    </td>
                </tr>
                <tr>
                    <td><spring:label path="age">Age</spring:label></td>
                    <td>
                        <spring:select path="age">
                            <spring:options itemValue="name" itemLabel="name" items="${ages}" />
                        </spring:select>
                    </td>
                </tr>
                <tr>
                    <td><spring:label path="color">Color</spring:label></td>
                    <td><spring:input path="color" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Create Listing" /></td>
                </tr>
            </table>
        </spring:form>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>