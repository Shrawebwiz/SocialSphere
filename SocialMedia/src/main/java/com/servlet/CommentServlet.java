package com.servlet;

import com.DAO.CommentDAO;
import com.DAO.CommentDAOImpl;
import com.model.Comment;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String content = request.getParameter("content");
        String postIdStr = request.getParameter("postId");

        if (content == null || postIdStr == null || content.trim().isEmpty()) {
            response.sendRedirect("viewPosts.jsp");
            return;
        }

        int postId = Integer.parseInt(postIdStr);

        Comment comment = new Comment(content.trim(), postId, user.getId());
        comment.setCreatedAt(LocalDateTime.now());

        CommentDAO commentDAO = new CommentDAOImpl();
        boolean success = commentDAO.addComment(comment);
        response.sendRedirect("viewPosts.jsp");
    }
}
