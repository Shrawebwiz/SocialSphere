<%@ page import="java.util.List" %>
<%@ page import="com.model.Post" %>
<%@ page import="com.model.Comment" %>
<%@ page import="com.DAO.PostDAO" %>
<%@ page import="com.DAO.PostDAOImpl" %>
<%@ page import="com.DAO.CommentDAO" %>
<%@ page import="com.DAO.CommentDAOImpl" %>
<%@ page import="com.DAO.UserDAO" %>
<%@ page import="com.DAO.UserDAOImpl" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page session="true" %>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    PostDAO postDAO = new PostDAOImpl();
    List<Post> posts = postDAO.viewAllPosts();

    CommentDAO commentDAO = new CommentDAOImpl();
    UserDAO userDAO = new UserDAOImpl();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Posts</title>
</head>
<body>
    <h2>All Posts</h2>

    <%
        if (posts.isEmpty()) {
    %>
        <p>No posts available.</p>
    <%
        } else {
            for (Post post : posts) {
                String username = userDAO.getUsernameById(post.getUserId());
    %>
        <div style="border:1px solid #ccc; padding:10px; margin-bottom:15px;">
            <h3><%= post.getTitle() %></h3>
            <p><%= post.getContent() %></p>
            <small>Posted by <strong><%= username %></strong> on <%= post.getCreatedAt().format(formatter) %></small>

            <form action="CommentServlet" method="post" style="margin-top:10px;">
                <input type="hidden" name="postId" value="<%= post.getId() %>">
                <textarea name="content" rows="2" cols="40" placeholder="Write a comment..." required></textarea><br>
                <input type="submit" value="Comment">
            </form>

            <%
                List<Comment> comments = commentDAO.getCommentsByPostId(post.getId());
                if (!comments.isEmpty()) {
            %>
                <div style="margin-top:10px; padding-left:15px;">
                    <h4>Comments:</h4>
                    <ul>
                        <% for (Comment c : comments) { %>
                            <li>
                                <strong><%= userDAO.getUsernameById(c.getUserId()) %></strong>: <%= c.getContent() %><br>
                                <small><%= c.getTimeStamp().format(formatter) %></small>
                            </li>
                        <% } %>
                    </ul>
                </div>
            <% } else { %>
                <p style="margin-top:10px; color:gray;">No comments yet.</p>
            <% } %>
        </div>
    <%
            }
        }
    %>
    <p><a href="createPost.jsp">Create New Post</a> | <a href="home.jsp">Back to Home</a></p>
</body>
</html>
