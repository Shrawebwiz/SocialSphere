package com.servlet;

import com.DAO.UserDAO;
import com.DAO.UserDAOImpl;
import com.model.User;

import com.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email    = request.getParameter("email");

        UserDAO userDAO = new UserDAOImpl();

        if (userDAO.checkUserExists(username)) {
            out.println("<h3 style='color:red;'>Username already exists. Try a different one.</h3>");
        } else {
            User user = new User(username, password, email);
            boolean registered = userDAO.registerUSer(user);

            if (registered) {
                out.println("<h3 style='color:green;'>Registration successful!</h3>");
                out.println("<a href='login.jsp'>Go to Login</a>");
            } else {
                out.println("<h3 style='color:red;'>Registration failed. Try again.</h3>");
            }
        }
        try {
            ResultSet rs = DBUtil.getConnection().createStatement()
                    .executeQuery("select table_name from information_schema.tables where table_schema='public'");

            while (rs.next()) {
                System.out.println("Table found: " + rs.getString("table_name"));
            }
        } catch (Exception e) {
            System.out.println("Failed to list tables.");
            e.printStackTrace();
        }

        out.close();
    }
}
