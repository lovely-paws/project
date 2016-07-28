<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actions for Shelters</title>
</head>
<body>
<h1>Actions for Shelters</h1>
<ul>
    <li>Approve Requests</li>
    <li><a href="${pageContext.request.contextPath}/listing/create">Create Listing</a></li>
    <li><a href="${pageContext.request.contextPath}/listing/?shelterId=${shelter.id}">View Listings</a></li>
</ul>
</body>
</html>
