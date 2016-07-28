<%@ page import="static edu.johnshopkins.lovelypaws.LovelyPawsConstants.ATTRIBUTE_USER_ID" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws Pet Adoption Site</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <h1>Welcome to Lovely Paws!</h1>
    <p>
        Lovely Paws strives to connect pets at shelters with new owners. The recipe is quite simple - shelters
        list the pets, and potential owners can search these listings. When one or more matches are made,
        the potential owner submits an application. Once approved by the shelter, the new owner
        and shelter can meet to arrange payment and take the pet home.
    </p>
    <p>
        Why wait - start <a href="${pageContext.request.contextPath}/listing">searching listings</a> today!
    </p>
</body>
</html>
