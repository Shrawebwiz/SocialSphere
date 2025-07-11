<%@ page import="com.model.User" %>
<%@ page session="true" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp"); // changed from login.html to login.jsp for consistency
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Home - Social Media</title>
</head>
<body>
    <h2>Welcome, <%= user.getUserName() %>!</h2>
    <p>Your email: <%= user.getEmail() %></p>

    <hr>

    <h3>Navigation</h3>
    <ul>
        <li><a href="viewPosts.jsp">1) View All Posts</a></li>
        <li><a href="createPost.jsp">2)️ Create New Post</a></li>
        <li><a href="viewMyPosts.jsp">3) My Posts</a></li>
        <li><a href="LogoutServlet">4) Logout</a></li>
    </ul>
</body>
</html>
