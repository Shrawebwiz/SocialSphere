package com.DAO;

import com.model.Post;
import com.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO {

    @Override
    public boolean createPost(Post post) {
        try {
            String sql = "insert into posts(title, content, created_at, user_id) values(?, ?, ?, ?)";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(post.getCreatedAt()));
            preparedStatement.setInt(4, post.getUserId());

            int res = preparedStatement.executeUpdate();
            return res > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Post> viewAllPosts() {
        List<Post> posts = new ArrayList<>();
        try {
            String sql = "select * from posts order by created_at DESC";
            Statement stmt = DBUtil.getConnection().createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at").toLocalDateTime(),
                        resultSet.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public List<Post> viewAllPostsByUserId(int userId) {
        List<Post> posts = new ArrayList<>();
        try {
            String sql = "select * from posts where user_id = ? order by created_at DESC";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at").toLocalDateTime(),
                        resultSet.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    @Override
    public int getPostCountByUserId(int userId) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM posts WHERE user_id = ?";
            PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
