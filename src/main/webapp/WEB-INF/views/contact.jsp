<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lovely Paws: Contact Us</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.less" />
</head>
<body>
    <div class="content">
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <h1>Contact Us</h1>
        <p>
            Have questions or want to learn more about our network? You can contact us by phone, snail mail, or e-mail using the
            information below.
        </p>
        <h2>Hours</h2>
        <ul>
            <li>Monday - Friday: 8am - 8pm ET</li>
            <li>Saturday: 9am - 7pm ET</li>
            <li>Sunday: 10am - 4pm</li>
        </ul>
        <h2>Address</h2>
        <p>
            555 Five Street<br />
            Columbia, MD 21045
        </p>
        <h2>Phone Number</h2>
        <p>555-555-5555</p>
        <h2>Send Us a Message!</h2>
        <p>You can use the following form to e-mail us.</p>
        <spring:form action="${pageContext.request.contextPath}/contact.do" method="POST" modelAttribute="contactInfo">
            <table>
                <tr>
                    <td><spring:label path="sender">Your Address</spring:label></td>
                    <td><spring:input path="sender" /></td>
                </tr>
                <tr>
                    <td><spring:label path="message">Message</spring:label></td>
                    <td><spring:textarea path="message" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Send Message" /></td>
                </tr>
            </table>
        </spring:form>
        <jsp:include page="/WEB-INF/views/footer.jsp" />
    </div>
</body>
</html>