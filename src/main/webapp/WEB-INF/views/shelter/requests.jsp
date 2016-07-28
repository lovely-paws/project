<%@ page import="edu.johnshopkins.lovelypaws.entity.AdoptionRequest" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<h1>Pending Requests</h1>
<%
    List<AdoptionRequest> adoptionRequests = (List<AdoptionRequest>)request.getAttribute("requests");
    for(AdoptionRequest adoptionRequest : adoptionRequests) { %>
<div>
    <h2><a href="${pageContext.request.contextPath}/listing/view/<%= adoptionRequest.getListing().getId()%>"><%= adoptionRequest.getListing().getName() %></a></h2>
    <h3>Adopter</h3>
    <p><%= adoptionRequest.getEndUser().getName() %></p>
    <p><%= adoptionRequest.getApplication().getWhy() %></p>
    <a href="${pageContext.request.contextPath}/shelter/close/<%= adoptionRequest.getId() %>?result=true">Accept</a>
    <a href="${pageContext.request.contextPath}/shelter/close/<%= adoptionRequest.getId() %>?result=false">Deny</a>
</div>
<% } %>
</body>
</html>
