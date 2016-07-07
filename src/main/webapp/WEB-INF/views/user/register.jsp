<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register as User</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Home</a>
<form action="create" method="POST">
    <div>
        <label name="username">Username</label>
        <input type="text" name="username" />
    </div>
    <div>
        <label name="password">Password</label>
        <input type="password" name="passwordSha512" />
    </div>
    <div>
        <label name="name">Name</label>
        <input type="text" name="name" />
    </div>
    <div>
        <label>E-Mail Address</label>
        <input type="text" name="emailAddress" />
    </div>
    <input type="submit" value="Register" />
</form>
</body>
</html>
