<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.johnshopkins.lovelypaws.beans.UserInfo" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.EndUser" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page import="edu.johnshopkins.lovelypaws.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserInfo userInfo = (UserInfo)(session.getAttribute("userInfo"));
%>

<div class="navigation">

	<div class="dropdown">
	  <button class="dropbtn" onclick="location.href='${pageContext.request.contextPath}/';">Home</button>
	</div>
	
	<div class="dropdown">
	  <button class="dropbtn">My Account</button>
	  <div class="dropdown-content">
			<% if(userInfo.getUser() != null) { %>
	                <a href="${pageContext.request.contextPath}/account/">My Account (${userInfo.user.username})</a>
	                <% if(userInfo.getUser() instanceof EndUser) { %>
	                    <a href="${pageContext.request.contextPath}/cart">My Cart</a>
	                <% } else if(userInfo.getUser() instanceof Shelter) { %>
	                   <a href="${pageContext.request.contextPath}/shelter/requests">Approve Requests</a></li>
	                    <a href="${pageContext.request.contextPath}/listing/create">Create Listing</a></li>
	                    <a href="${pageContext.request.contextPath}/listing/?shelterId=${userInfo.user.id}">View Listings</a>
	                <% } else if (userInfo.getUser().getRole() == Role.ADMINISTRATOR) { %>
	                     <a href="${pageContext.request.contextPath}/animal-type/">Animal Types</a>
	                <% } %>
	                <a href="${pageContext.request.contextPath}/logout">Log Out</a>
	            <% } else { %>
	                <a href="${pageContext.request.contextPath}/shelter/register">Register as Shelter</a>
	                <a href="${pageContext.request.contextPath}/user/register">Register as User</a>
	                <a href="${pageContext.request.contextPath}/login">Log In</a>
	            <% } %>
	  </div>
	</div>
	
	<div class="dropdown">
	  <button class="dropbtn">Our Pets</button>
	  <div class="dropdown-content">
	            <a href="${pageContext.request.contextPath}/listing">Search Pets</a>
	            <li class="nav-li-2"><a href="${pageContext.request.contextPath}/shelter">View Shelters</a>
	  </div>
	</div>
	
	 <div class="dropdown">
	  <button class="dropbtn">Our Pets</button>
	  <div class="dropdown-content">
	            <a href="${pageContext.request.contextPath}/">Search Pets</a>
	            <a href="${pageContext.request.contextPath}/">Contact Us</a>
	  </div>
	</div>
 </div>
 
<% if(request.getAttribute("message") != null) { %>
<div class="message-dialog">Message: <c:out value="${message}" escapeXml="true"/></div>
<% } %>
