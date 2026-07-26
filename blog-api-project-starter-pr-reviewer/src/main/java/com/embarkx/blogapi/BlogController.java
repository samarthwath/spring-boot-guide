package com.embarkx.blogapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.*;

@RestController
@RequestMapping("/api/posts")
public class BlogController {

    private static List<String> posts = new ArrayList<>();

    @Value("${blog.content.max-length}")
    private int maxContentLength;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Posts request) {
        String title = request.title();
        String content = request.content();
        if (title == null || title.isBlank() || content == null || content.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title and content must not be empty");
        }
        if (content.length() < 10 || content.length() > 500) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Content must be between 10 and 500 characters");
        }
        String post = title + ":" + content;
        posts.add(post);

        return ResponseEntity.ok("Post created");
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllPosts() {
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPost(@PathVariable int id) {
        if (id < 0 || id >= posts.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found for id " + id);
        }
        return ResponseEntity.ok(posts.get(id));
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateContent(@RequestParam String content) {
        if (content.length() > maxContentLength) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Too long");
        }
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        if (id < 0 || id >= posts.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found for id " + id);
        }
        posts.remove(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/total")
    public ResponseEntity<String> getTotalWordCount() {
        List<String> wordCounts = List.of("100", "200", "300");
        int total = 0;
        for (String count : wordCounts) {
            total += Integer.parseInt(count);
        }
        return ResponseEntity.ok("Total words: " + total);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable int id, @RequestBody Posts request) {
        String title = request.title();
        String content = request.content();

        if (id < 0 || id >= posts.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found for id " + id);
        }

        if (title == null || title.isBlank() || content == null || content.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title and content must not be empty");
        }

        if (content.length() < 10 || content.length() > 500) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Content must be between 10 and 500 characters");
        }

        String post = title + ":" + content;
        posts.set(id, post);
        return ResponseEntity.ok("Post updated");
    }



}