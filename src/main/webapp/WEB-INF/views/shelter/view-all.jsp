<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lp" uri="http://lovelypaws.com/tags.tld" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: View Shelters</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <% List<Shelter> shelters = (List<Shelter>)request.getAttribute("shelters"); %>
    <h1>Browse Shelters</h1>
    <% if(shelters == null || shelters.size() == 0) { %>
        <p>No results!</p>
    <% } else {
        for(Shelter shelter : shelters) {
            request.setAttribute("shelter", shelter);
        %>
            <lp:userTag user="${shelter}" viewer="${userInfo.user}" baseUrl="${pageContext.request.contextPath}" />
        <% }
    } %>
</body>
</html>
