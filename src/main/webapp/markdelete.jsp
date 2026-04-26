<%@ page language="java" %>
<html>
<head>
<title>Delete Marks</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<h2>Delete Student Record</h2>

<form action="deleteMark" method="post">
    Enter Student ID:
    <input type="text" name="id" required>

    <input type="submit" value="Delete">
</form>

<a href="index.jsp">Back</a>

</body>
</html>