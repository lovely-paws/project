<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Checkout</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<h1>Checking Out</h1>
<p>
    Please complete the following adoption questionnaire. Your responses will be submitted
    to the organization(s) currently housing the pet(s) you have selected.
</p>
<ul>
<% for(Listing listing : (List<Listing>)request.getAttribute("listings")) { %>
    <li><%= listing.getName() %></li>
<% } %>
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
</body>
</html>
