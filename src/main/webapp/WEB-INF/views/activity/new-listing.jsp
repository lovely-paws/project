<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Listing</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Home</a>
<form action="../listing/create" method="POST">
    <div>
        <label name="name">Name</label>
        <input type="text" name="name" />
    </div>
    <div>
        <label name="description">Description</label>
        <input type="text" name="description" />
    </div>
    <div>
        <label name="color">Color (BLACK)</label>
        <input type="text" name="color" />
    </div>
    <input type="submit" value="Register" />

</form>
</body>
</html>
