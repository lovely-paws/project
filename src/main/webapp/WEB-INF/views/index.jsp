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
    <p>Would you like to <a href="shelter/register">Register as a Shelter</a>?</p>
        <p>Maybe you would like to <a href="session/login">login</a>?</p>
    <% } else { %>
        <p>Why not create <a href="listing/create">a listing</a>?</p>
        <p>Maybe you would like to <a href="session/logout">logout</a>?</p>
    <% } %>
</body>
</html>
