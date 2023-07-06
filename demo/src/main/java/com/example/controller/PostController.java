package com.example.controller;

import com.example.model.BlogPost;
import com.example.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final BlogPostService blogPostService;

    @Autowired
    public PostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("")
    public String getAllBlogPosts(Model model) {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
        model.addAttribute("blogPosts", blogPosts);
        return "list";
    }

    @GetMapping("/{id}")
    public String getBlogPostById(@PathVariable("id") Long id, Model model) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if (blogPost != null) {
            model.addAttribute("blogPost", blogPost);
            return "post";
        } else {
            return "not_found";
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        return "create";
    }

    @PostMapping("")
    public String createBlogPost(@ModelAttribute("blogPost") BlogPost blogPost) {
        blogPostService.createBlogPost(blogPost);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if (blogPost != null) {
            model.addAttribute("blogPost", blogPost);
            return "edit";
        } else {
            return "not_found";
        }
    }

    @PostMapping("/{id}")
    public String updateBlogPost(@PathVariable("id") Long id, @ModelAttribute("blogPost") BlogPost updatedBlogPost) {
        BlogPost existingBlogPost = blogPostService.getBlogPostById(id);
        if (existingBlogPost != null) {
            existingBlogPost.setTitle(updatedBlogPost.getTitle());
            existingBlogPost.setContent(updatedBlogPost.getContent());
            existingBlogPost.setAuthor(updatedBlogPost.getAuthor());
            blogPostService.updateBlogPost(existingBlogPost);
            return "redirect:/posts";
        } else {
            return "not_found";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteBlogPost(@PathVariable("id") Long id) {
        blogPostService.deleteBlogPost(id);
        return "redirect:/posts";
    }

}
