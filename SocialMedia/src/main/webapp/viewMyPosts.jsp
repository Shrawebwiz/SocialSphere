<%@ page import="java.util.List" %>
<%@ page import="com.model.Post" %>
<%@ page import="com.DAO.PostDAO" %>
<%@ page import="com.DAO.PostDAOImpl" %>
<%@ page import="com.model.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page session="true" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    PostDAO postDAO = new PostDAOImpl();
    List<Post> posts = postDAO.viewAllPostsByUserId(user.getId());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Posts</title>
</head>
<body>
    <h2>Posts by You (<%= user.getUserName() %>)</h2>

    <%
        if (posts.isEmpty()) {
    %>
        <p>You haven't posted anything yet.</p>
    <%
        } else {
            for (Post post : posts) {
    %>
        <div style="border:1px solid #ccc; padding:10px; margin-bottom:15px;">
            <h3><%= post.getTitle() %></h3>
            <p><%= post.getContent() %></p>
            <small>Posted on <%= post.getCreatedAt().format(formatter) %></small>
        </div>
    <%
            }
        }
    %>

    <p><a href="createPost.jsp">Create New Post</a> | <a href="home.jsp">Back to Home</a></p>
</body>
</html>
