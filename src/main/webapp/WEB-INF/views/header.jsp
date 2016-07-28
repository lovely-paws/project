<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.johnshopkins.lovelypaws.beans.UserInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserInfo userInfo = (UserInfo)(session.getAttribute("userInfo"));
%>
<div>My Account</div>
<ul>
    <% if(userInfo.getUser() != null) { %>
        <li><a href="${pageContext.request.contextPath}/account/">My Account (${userInfo.user.username})</a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Log Out</a></li>
    <% } else { %>
        <li><a href="${pageContext.request.contextPath}/shelter/register">Register as Shelter</a></li>
        <li><a href="${pageContext.request.contextPath}/user/register">Register as User</a></li>
        <li><a href="${pageContext.request.contextPath}/login">Log In</a></li>
    <% } %>
</ul>
<div>Our Pets</div>
<ul>
    <li><a href="${pageContext.request.contextPath}/listing">Search Pets</a></li>
</ul>
<div>About Us</div>
<ul>
    <li>Our History</li>
    <li>Contact Us</li>
</ul>

<% if(request.getAttribute("message") != null) { %>
<div>Message: <c:out value="${message}" escapeXml="true"/></div>
<% } %>