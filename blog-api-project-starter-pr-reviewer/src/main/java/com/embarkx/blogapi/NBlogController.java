package com.embarkx.blogapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class NBlogController {

    @Autowired
    private NBlogService nBlogService;

    /*private final NBlogService nBlogService;

    public NBlogController(NBlogService nBlogService) {
        this.nBlogService = nBlogService;
    }*/

    @PostMapping
    public ResponseEntity<String> createPost(@RequestParam String title, @RequestParam String content) {
        nBlogService.createPost(title, content);
        return ResponseEntity.ok("Post created");
    }

    @GetMapping
    public ResponseEntity<List<NewPost>> getAllPosts() {
        List<NewPost> posts = nBlogService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPostByTitle(@RequestParam String title) {
        try {
            return ResponseEntity.ok(nBlogService.searchPostByTitle(title));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(nBlogService.getPostById(id));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateContent(@RequestParam String content) {
        try {
            nBlogService.validateContentLength(content);
            return ResponseEntity.ok("OK");
        } catch (InvalidPostException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable UUID id) {
        try {
            nBlogService.deletePost(id);
            return ResponseEntity.ok("Deleted");
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/total")
    public String getTotalWordCount() {
        List<String> wordCounts = List.of("100", "200", "300");
        String total = "";
        for (String count : wordCounts) {
            total += count;
        }
        return "Total words: " + total;
    }
}
