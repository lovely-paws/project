<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Checkout</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />

        <h1>Checking Out</h1>
        <p>
            Please complete the following adoption questionnaire. Your responses will be submitted
            to the organization(s) currently housing the pet(s) you have selected. The shelter
            will contact you when they have received your application and made a determination.
        </p>
        <ul>
            <c:forEach var="listing" items="${listings}">
                <li>${listing.name}</li>
            </c:forEach>
        </ul>
            <spring:form action="apply" method="POST" modelAttribute="applicationInfo">
                <table>
                    <tr>
                        <td><spring:label path="why">Why do you want do adopt this pet or these pets?</spring:label></td>
                        <td><spring:textarea path="why" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><spring:checkbox path="accepted" /><spring:label path="accepted">I agree to be an outstanding owner.</spring:label></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Complete Application" /></td>
                    </tr>
                </table>
            </spring:form>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>
