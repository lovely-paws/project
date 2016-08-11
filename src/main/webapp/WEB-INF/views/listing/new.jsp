<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Create A Listing</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <spring:form action="#" method="POST" modelAttribute="listingInfo" enctype="multipart/form-data">
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
                    <td><spring:label path="age">Gender</spring:label></td>
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
                    <td><label for="uploadedFile">Image (JPG)</label></td>
                    <td><input type="file" id="uploadedFile" name="uploadedFile" accept="image/jpeg" /></td>
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
