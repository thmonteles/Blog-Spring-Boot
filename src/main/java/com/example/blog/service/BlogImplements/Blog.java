package com.example.blog.service.BlogImplements;

import com.example.blog.model.Post;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Blog implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Post> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Post post = blogRepository.findById(id).orElse(null);

        if (post != null) {
            return ResponseEntity.ok().body(post);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado para o ID: " + id);
    }

    @Override
    public Post save(Post post) {
        return blogRepository.save(post);
    }
}
