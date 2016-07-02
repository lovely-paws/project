<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register as Shelter</title>
</head>
<body>
    <form action="create" method="POST">
        <div>
            <label name="username">Username</label>
            <input type="text" name="username" />
        </div>
        <div>
            <label name="password">Password</label>
            <input type="password" name="password" />
        </div>
        <div>
            <label name="name">Name</label>
            <input type="text" name="name" />
        </div>
        <div>
            <label name="description">Description</label>
            <input type="text" name="description" />
        </div>
        <div>
            <label name="phoneNumber">Phone Number</label>
            <input type="text" name="phoneNumber" />
        </div>

        <div>
            <label name="line1">Address Line 1</label>
            <input type="text" name="line1" />
        </div>
        <div>
            <label name="line2">Address Line 2</label>
            <input type="text" name="line2" />
        </div>
        <div>
            <label name="city">City</label>
            <input type="text" name="city" />
        </div>
        <div>
            <label name="state">State</label>
            <input type="text" name="state" />
        </div>
        <div>
            <label name="zip">Zip Code</label>
            <input type="text" name="zip" />
        </div>
        <div>
            <!-- TODO: ANIMAL TYPES -->
            TODO: ANIMAL TYPES
        </div>
        <input type="submit" value="Register" />

    </form>
</body>
</html>
