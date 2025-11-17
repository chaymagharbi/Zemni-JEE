<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>    
</head>
<body>
<c:out value="${requestScope.error}"/>
    <form action="Controller" method="post">
        <label>login Etudiant:</label>
        <input type="text" name="login"/>
        <label>password:</label>
        <input type="password" name="password"/>
        <button type="submit" name="myBtn" value="Login">Login</button>
    </form>
</body>
</html>