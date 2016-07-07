<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/">Home</a>

    <% if(request.getSession().getAttribute("userId") == null) { %>
        <h1>You are not logged in.</h1>
    <p>Would you like to <a href="${pageContext.request.contextPath}/user/register">Register as an End User</a>?</p>
        <p>Would you like to <a href="${pageContext.request.contextPath}/shelter/register">Register as a Shelter</a>?</p>
        <p>Maybe you would like to <a href="${pageContext.request.contextPath}/session/login">login</a>?</p>

    <% } else { %>
        <h1>You are logged in.</h1>
        <p><a href="${pageContext.request.contextPath}/account">My Account</a> </p>
        <p>Why not create <a href="${pageContext.request.contextPath}/listing/create">a listing</a>?</p>
        <p>Maybe you would like to <a href="${pageContext.request.contextPath}/session/logout">logout</a>?</p>
    <% } %>

    <p>Maybe you would like to VIEW <a href="${pageContext.request.contextPath}/listing">listings</a>?</p>
</body>
</html>
