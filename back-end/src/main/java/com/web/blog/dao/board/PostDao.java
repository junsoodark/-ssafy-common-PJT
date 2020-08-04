package com.web.blog.dao.board;

import com.google.common.base.Optional;
import com.web.blog.model.board.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, String> {
    Optional<Post> searchPostByTitle(String title);

    Optional<Post> searchPostById(int id);
}