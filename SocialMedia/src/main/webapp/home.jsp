<%@ page import="com.DAO.PostDAO" %>
<%@ page import="com.DAO.PostDAOImpl" %>
<%@ page import="com.DAO.CommentDAO" %>
<%@ page import="com.DAO.CommentDAOImpl" %>

<%@ page import="com.model.User" %>
<%@ page session="true" %>


<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
 <%
        PostDAO postDAO = new PostDAOImpl();
        CommentDAO commentDAO = new CommentDAOImpl();

        int postCount = postDAO.getPostCountByUserId(user.getId());
        int commentCount = commentDAO.getCommentCountByUserId(user.getId());
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
<h3> DashBoard </h3>
<ul>
    <li>Total Posts: <strong><%= postCount %></strong></li>
    <li>Total Comments: <strong><%= commentCount %></strong></li>
</ul>
<hr>

</body>
</html>
