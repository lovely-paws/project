<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Register as Shelter</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.less" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <h1>Register Your Shelter</h1>
        <spring:form action="create" method="POST" modelAttribute="shelterInfo">
            <table>
                <tr>
                    <td><spring:label path="username">Username</spring:label></td>
                    <td><spring:input path="username" /></td>
                </tr>
                <tr>
                    <td><spring:label path="password">Password</spring:label></td>
                    <td><spring:password path="password" /></td>
                </tr>
                <tr>
                    <td><spring:label path="name">Name</spring:label></td>
                    <td><spring:input path="name" /></td>
                </tr>
                <tr>
                    <td><spring:label path="emailAddress">E-Mail Address</spring:label></td>
                    <td><spring:input path="emailAddress" /></td>
                </tr>
                <tr>
                    <td><spring:label path="description">Description</spring:label></td>
                    <td><spring:textarea path="description" /></td>
                </tr>
                <tr>
                    <td><spring:label path="phoneNumber">Phone Number</spring:label></td>
                    <td><spring:input path="phoneNumber" /></td>
                </tr>
                <tr>
                    <td><spring:label path="addressInfo.line1">Address Line 1</spring:label></td>
                    <td><spring:input path="addressInfo.line1" /></td>
                </tr>
                <tr>
                    <td><spring:label path="addressInfo.line2">Address Line 2</spring:label></td>
                    <td><spring:input path="addressInfo.line2" /></td>
                </tr>
                <tr>
                    <td><spring:label path="addressInfo.city">City</spring:label></td>
                    <td><spring:input path="addressInfo.city" /></td>
                </tr>
                <tr>
                    <td><spring:label path="addressInfo.state">State</spring:label></td>
                    <td><spring:input path="addressInfo.state" /></td>
                </tr>
                <tr>
                    <td><spring:label path="addressInfo.zip">ZIP Code</spring:label></td>
                    <td><spring:input path="addressInfo.zip" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Register" /></td>
                </tr>
            </table>
        </spring:form>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>
