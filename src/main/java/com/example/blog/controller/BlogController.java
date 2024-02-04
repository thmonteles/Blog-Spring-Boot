package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {
    @Autowired
    private BlogRepository postRepository;

    @GetMapping("/blog")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "blog";
    }

    // Cria um novo post
    @GetMapping("/blog/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "create";
    }
    @PostMapping("/blog/create")
    public String createPost(@ModelAttribute Post post) {
        postRepository.save(post);
        return "blog";
    }

    // Edita um post existente
    @GetMapping("/blog/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        if( post != null){
            model.addAttribute("post", post);
            return "edit";
        }
        return "error";
    }

    @PostMapping("/blog/edit/{id}")
    public String editPost(@PathVariable Long id, @ModelAttribute Post updatePost) {
        Post postExistente = postRepository.findById(id).orElse(null);


        if (postExistente != null) {
            postExistente.setTitle(updatePost.getTitle());
            postExistente.setContent(updatePost.getContent());

            postRepository.save(postExistente);

            return "redirect:/blog";
        }

        return "error";
    }


    // Exclui um post
    @GetMapping("/blog/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/blog";
    }
}
