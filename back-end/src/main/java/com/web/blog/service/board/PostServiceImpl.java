package com.web.blog.service.board;

import java.util.Optional;

import com.web.blog.dao.board.PostDao;
import com.web.blog.model.board.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostDao postDao;

    @Override
    public Post findPostByTitle(final String title) {
        Optional<Post> postOpt = postDao.findPostByTitle(title);
        return postOpt.isPresent() ? postOpt.get() : null;
    }

    @Override
    public boolean create(Post post) {
        if (postDao.findPostById(post.getId()).isPresent())
            return false;
        postDao.save(post);
        return true;
    }

    @Override
    public boolean update(Post post) {
        Optional<Post> postOpt = postDao.findPostById(post.getId());
        if (postOpt.isPresent() == false)
            return false;

        postOpt.ifPresent(p -> {
            p.setContent(post.getContent());
            p.setStudy(post.getStudy());
            p.setTitle(post.getTitle());
            p.setUser(post.getUser());
            postDao.save(p);
        });

        return true;
    }

    @Override
    public boolean delete(int id) {
        Optional<Post> postOpt = postDao.findPostById(id);
        if (postOpt.isPresent() == false)
            return false;

        postOpt.ifPresent(post -> {
            postDao.delete(post);
        });
        return true;
    }

    @Override
    public Post findPostById(int id) {
        Optional<Post> postOpt = postDao.findPostById(id);
        return postOpt.isPresent() ? postOpt.get() : null;
    }

}