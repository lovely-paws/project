<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
	<ul class="login">
		<c:choose>
			<c:when test="${not empty userInfo.user}">
				<li class="dropdown">
					<a href="${pageContext.request.contextPath}/account" class="dropdown-button">My Account (${userInfo.user.username})</a>
					<ul class="dropdown-content">
						<c:choose>
							<c:when test="${userInfo.user.role eq 'END_USER'}">
								<a href="${pageContext.request.contextPath}/cart">My Cart</a>
							</c:when>
							<c:when test="${userInfo.user.role eq 'SHELTER'}">
								<a href="${pageContext.request.contextPath}/shelter/requests">Approve Requests</a>
								<a href="${pageContext.request.contextPath}/listing/create">Create Listing</a>
								<a href="${pageContext.request.contextPath}/listing/?shelterId=${userInfo.user.id}">View Listings</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/animal-type/">Animal Types</a>
								<a href="${pageContext.request.contextPath}/listing/">View Listings</a>
								<a href="${pageContext.request.contextPath}/user/">View Users</a>
							</c:otherwise>
						</c:choose>
					</ul>
				</li>
				<li class="dropdown">
					<button class="dropdown-button" onclick="location.href='${pageContext.request.contextPath}/logout';">Logout</button>
				</li>
			</c:when>
			<c:otherwise>
				<div class="dropdown">
					<button class="dropdown-button">Register</button>
					<div class="dropdown-content">
						<a href="${pageContext.request.contextPath}/shelter/register">Register as Shelter</a>
						<a href="${pageContext.request.contextPath}/user/register">Register as User</a>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropdown-button" onclick="location.href='${pageContext.request.contextPath}/login';">Login</button>
				</div>
			</c:otherwise>
		</c:choose>
	</ul>

	<div class="logo">
		<img src="${pageContext.request.contextPath}/img/logo.png" />
	</div>

	<ul class="navigation">
		<li class="dropdown">
			<button class="dropdown-button" onclick="location.href='${pageContext.request.contextPath}/';">Home</button>
		</li>
		<li class="dropdown">
			<button class="dropdown-button">Our Pets</button>
			<ul class="dropdown-content">
				<li><a href="${pageContext.request.contextPath}/listing/">Search Pets</a><li>
				<li><a href="${pageContext.request.contextPath}/shelter/">View Shelters</a><li>
			</ul>
		</li>
		<li class="dropdown">
			<button class="dropdown-button">About Us</button>
			<ul class="dropdown-content">
				<li><a href="${pageContext.request.contextPath}/about">About</a><li>
				<li><a href="${pageContext.request.contextPath}/contact-us">Contact Us</a><li>
			</ul>
		</li>
	</ul>
</div><div class="no-height cleared"></div>

<div class="content-body">
	<c:if test="${not empty message}">
		<div class="message-dialog">Message: <c:out value="${message}" escapeXml="true"/></div>
	</c:if>


