<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Browse Listings</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.less" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <h1>Search Listings</h1>
        <spring:form action="#" method="get" modelAttribute="listingSearch">
            <table>
                <tr>
                    <td><spring:label path="animalTypeId" >Animal Type</spring:label></td>
                    <td>
                        <spring:select path="animalTypeId">
                            <spring:option value="">Any Type</spring:option>
                            <spring:options items="${animalTypes}" itemValue="id" itemLabel="name" />
                        </spring:select>
                    </td>
                </tr>
                <tr>
                    <td><spring:label path="shelterId" cssClass="search-label">Shelter</spring:label></td>
                    <td>
                        <spring:select path="shelterId" cssClass="search-select">
                            <spring:option value="">Any Shelter</spring:option>
                            <spring:options items="${shelters}" itemValue="id" itemLabel="name" cssClass="search-option" />
                        </spring:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Search" /></td>
                </tr>
            </table>
        </spring:form>

        <c:choose>
            <c:when test="${empty listings}">
                <p>No matches found!</p>
            </c:when>
            <c:otherwise>
                <c:forEach var="listing" items="${listings}">
                    <lp:listingTag listing="${listing}" baseUrl="${pageContext.request.contextPath}" viewer="${userInfo.user}" detailed="false" />
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>
