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
