package com.servlet;

import com.DAO.PostDAO;
import com.DAO.PostDAOImpl;
import com.model.Post;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Post post = new Post(title, content, user.getId());
        post.setCreatedAt(LocalDateTime.now());

        PostDAO postDAO = new PostDAOImpl();
        boolean created = postDAO.createPost(post);

        if (created) {
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("error", "Failed to create post. Try again.");
            request.getRequestDispatcher("createPost.jsp").forward(request, response);
        }
    }
}
