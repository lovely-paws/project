<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% if(request.getAttribute("shelters") != null) {
    List<Shelter> shelters = (List<Shelter>)request.getAttribute("shelters");
    for(Shelter shelter : shelters) {
        request.setAttribute("shelter", shelter);
%>
<h1>Shelter #${shelter.id}: ${shelter.name}</h1>
<p>${shelter.description}</p>
<a href="shelter/view/${shelter.id}">Detailed View</a>
<% }
}%>
</body>
</html>
