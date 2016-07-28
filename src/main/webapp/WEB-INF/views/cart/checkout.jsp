<%@ page import="edu.johnshopkins.lovelypaws.entity.Listing" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
<h1>Checking Out</h1>
<p>
    Please complete the following adoption questionnaire. Your responses will be submitted
    to the organization(s) currently housing the pet(s) you have selected.
</p>
<ul>
<% for(Listing listing : (List<Listing>)request.getAttribute("listings")) { %>
    <li><%= listing.getName() %></li>
<% } %>
</ul>
<form action="apply" method="POST">
    <ul>
        <li>
            <label for="why">Why do you want do adopt this pet or these pets?</label>
            <input type="text" id="why" name="why" />
        </li>
        <li>
            <input type="checkbox" name="accepted" value="true" id="accept" /><label for="accept"></label>
        </li>
        <li>
            <input type="submit" value="Complete Application" />
        </li>
    </ul>
</form>
</body>
</html>
