package com.example.service;

import com.example.model.BlogPost;
import com.example.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }
    public void createBlogPost(BlogPost blogPost) {
        blogPostRepository.save(blogPost);
    }
    public BlogPost getBlogPostById(Long id) {
        Optional<BlogPost> optionalBlogPost = blogPostRepository.findById(id);
        return optionalBlogPost.orElse(null);
    }
    public void updateBlogPost(BlogPost blogPost) {
        blogPostRepository.save(blogPost);
    }
    public void deleteBlogPost(Long id) {
        blogPostRepository.deleteById(id);
    }
}
