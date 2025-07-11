<%@ page import="com.model.User" %>
<%@ page session="true" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Create Post</title>
</head>
<body>
    <h2>Create New Post</h2>

    <form action="PostServlet" method="post">
        <label>Title:</label><br>
        <input type="text" name="title" required><br><br>

        <label>Content:</label><br>
        <textarea name="content" rows="6" cols="40" required></textarea><br><br>

        <input type="submit" value="Post">
    </form>

    <p><a href="home.jsp">Back to Home</a></p>
</body>
</html>
