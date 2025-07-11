package com.DAO;

import com.model.User;
import com.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean registerUSer(User user) {
        try {
            String sql = "insert into users(username, password, email) values (?, ?, ?)";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            int res = preparedStatement.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User loginUserByName(String userName, String password) {
        try {
            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User loginUserByEmail(String email, String password) {
        try {
            String sql = "select * from users where email = ? and password = ?";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkUserExists(String userName) {
        try {
            String sql = "select id from users where username = ?";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        try {
            String sql = "select * from users where id = ?";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String getUsernameById(int userId) {
        try {
            String sql = "select username from users where id = ?";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

}
