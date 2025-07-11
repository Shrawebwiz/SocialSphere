<%@ page import="com.model.User" %>
<%@ page session="true" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String postIdParam = request.getParameter("postId");
    if (postIdParam == null) {
        response.sendRedirect("viewPosts.jsp");
        return;
    }

    int postId = Integer.parseInt(postIdParam);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Comment</title>
</head>
<body>
    <h2>Add a Comment</h2>

    <form action="CommentServlet" method="post">
        <input type="hidden" name="postId" value="<%= postId %>">
        <textarea name="content" rows="4" cols="50" placeholder="Write your comment..." required></textarea><br><br>
        <input type="submit" value="Submit Comment">
    </form>

    <p><a href="viewPosts.jsp">Back to Posts</a></p>
</body>
</html>
