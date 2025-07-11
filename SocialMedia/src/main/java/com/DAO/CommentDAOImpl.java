package com.DAO;

import com.model.Comment;
import com.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {

    @Override
    public boolean addComment(Comment comment) {
        try {
            String sql = "insert into comments(content, created_at, post_id, user_id) values (?, ?, ?, ?)";
            PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
            ps.setString(1, comment.getContent());
            ps.setTimestamp(2, Timestamp.valueOf(comment.getTimeStamp()));
            ps.setInt(3, comment.getPostId());
            ps.setInt(4, comment.getUserId());

            int res = ps.executeUpdate();
            return res > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();
        try {
            String sql = "select * from comments where post_id = ? order by created_at ASC";
            PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                comments.add(new Comment(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getInt("post_id"),
                        rs.getInt("user_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    @Override
    public int getCommentCountByUserId(int userId) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM comments WHERE user_id = ?";
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
