package com.embarkx.blogapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/posts")
public class BlogController {

    private final List<String> posts = new CopyOnWriteArrayList<>();

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPost(@RequestParam String title, @RequestParam String content) {
        String post = title + ":" + content;
        posts.add(post);
        int id = posts.size() - 1;
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", id, "message", "Post created"));
    }

    @GetMapping
    public List<String> getAllPosts() {
        return posts;
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable int id) {
        return posts.get(id);
    }

    @PostMapping("/validate")
    public String validateContent(@RequestParam String content) {
        if (content.length() > 5000) {
            return "Too long";
        }
        return "OK";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        posts.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public String getWordCount() {
        List<String> wordCounts = List.of("100", "200", "300");
        String total = "";
        for (String count : wordCounts) {
            total += count;
        }
        return "Total words: " + total;
    }
}