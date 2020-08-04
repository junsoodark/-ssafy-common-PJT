package com.web.blog.service.board;

import com.web.blog.model.board.Post;

public interface PostService {
    public Post findPostByTitle(String title);

    public boolean create(Post post);

    public boolean update(Post post);

    public boolean delete(int id);

    public Post findPostById(int id);
}