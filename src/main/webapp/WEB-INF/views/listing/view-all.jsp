<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Shelter" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% if(request.getAttribute("listings") != null) {
    List<Listing> listings = (List<Listing>)request.getAttribute("listings");
    for(Listing listing : listings) {
        request.setAttribute("listing", listing);
%>
<h1>${listing.id} ${listing.name}</h1>
<p>${listing.description}</p>
<p>${listing.color}</p>
<% }
}%>

<table>
    <thead>
    <tr>
        <th>Attribute</th>
        <th>Value</th>
    </tr>
    </thead>
    <tbody>
    <%
        Enumeration<String> attributeNames = request.getAttributeNames();
        while(attributeNames.hasMoreElements()) {
            String element = attributeNames.nextElement(); %>
    <tr>
        <td><%= HtmlUtils.htmlEscape(element) %></td>
        <td><%= HtmlUtils.htmlEscape(request.getAttribute(element) == null ? "" : request.getAttribute(element).toString()) %></td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
