package com.DAO;

import com.model.Post;

import java.util.List;

public interface PostDAO {

    boolean createPost(Post post);

    List<Post> viewAllPosts();

    List<Post> viewAllPostsByUserId(int userId);
    int getPostCountByUserId(int userId);
}
