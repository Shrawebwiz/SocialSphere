package com.DAO;

import com.model.User;

public interface UserDAO {

    public boolean registerUSer(User user);
    public User loginUserByName(String userName, String password);
    public User loginUserByEmail(String email, String password);
    public boolean checkUserExists(String userName);
    User getUserById(int id);
    String getUsernameById(int userId);

}
