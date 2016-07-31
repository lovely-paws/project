<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.johnshopkins.lovelypaws.beans.UserInfo" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.EndUser" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserInfo userInfo = (UserInfo)(session.getAttribute("userInfo"));
%>
<ul id="navigation">
    <li class="nav-li-1">
        <div class="nav-li-title">My Account</div>
        <ul class="nav-level-2">
            <% if(userInfo.getUser() != null) { %>
                <li class="nav-li-2"><a href="${pageContext.request.contextPath}/account/">My Account (${userInfo.user.username})</a></li>
                <% if(userInfo.getUser() instanceof EndUser) { %>
                    <li class="nav-li-2"><a href="${pageContext.request.contextPath}/cart">My Cart</a></li>
                <% } else if(userInfo.getUser() instanceof Shelter) { %>
                    <li class="nav-li-2"><a href="${pageContext.request.contextPath}/shelter/requests">Approve Requests</a></li>
                    <li class="nav-li-2"><a href="${pageContext.request.contextPath}/listing/create">Create Listing</a></li>
                    <li class="nav-li-2"><a href="${pageContext.request.contextPath}/listing/?shelterId=${userInfo.user.id}">View Listings</a></li>
                <% } %>
                <li class="nav-li-2"><a href="${pageContext.request.contextPath}/logout">Log Out</a></li>
            <% } else { %>
                <li class="nav-li-2"><a href="${pageContext.request.contextPath}/shelter/register">Register as Shelter</a></li>
                <li class="nav-li-2"><a href="${pageContext.request.contextPath}/user/register">Register as User</a></li>
                <li class="nav-li-2"><a href="${pageContext.request.contextPath}/login">Log In</a></li>
            <% } %>
        </ul>
    </li>
    <li class="nav-li-1">
        <div class="nav-li-title">Our Pets</div>
        <ul class="nav-level-2">
            <li class="nav-li-2"><a href="${pageContext.request.contextPath}/listing">Search Pets</a></li>
        </ul>
    </li>
    <li class="nav-li-1">
        <div class="nav-li-title">About Us</div>
        <ul class="nav-level-2">
            <li class="nav-li-2">Our History</li>
            <li class="nav-li-2">Contact Us</li>
        </ul>
    </li>
</ul>
<% if(request.getAttribute("message") != null) { %>
<div class="message-dialog">Message: <c:out value="${message}" escapeXml="true"/></div>
<% } %>