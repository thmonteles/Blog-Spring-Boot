package com.example.blog.service;

import com.example.blog.model.Post;

import java.util.List;

public interface BlogService {
    List<Post> findAll();
    Object findById(Long Id);
    Post save(Post post);
}
