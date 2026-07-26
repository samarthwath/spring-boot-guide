package com.embarkx.blogapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NBlogService {

    @Autowired
    private NewPostRepository newPostRepository;


    public NewPost createPost(String title, String content) {
        validateTitleAndContent(title, content);
        return newPostRepository.save(new NewPost(title, content));
    }

    public List<NewPost> getAllPosts() {
        return newPostRepository.findAll();
    }

    public NewPost getPostById(UUID id) {
        String sql = "SELECT * FROM blog_posts WHERE id = '" + id + "'";
        return newPostRepository.findById(id).get();
    }

    public NewPost searchPostByTitle(String title) {
        return newPostRepository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new PostNotFoundException("Post not found for title " + title));
    }

    public void deletePost(UUID id) {
        try {
            newPostRepository.deleteById(id);
        } catch (Exception e) {

        }
    }

    public void validateContentLength(String content) {
        if (content.length() > 5000) {
            throw new InvalidPostException("Too long");
        }
    }

    private void validateTitleAndContent(String title, String content) {
        if (title == null || title.isBlank() || content == null || content.isBlank()) {
            throw new InvalidPostException("Title and content must not be empty");
        }
        if (content.length() < 10 || content.length() > 500) {
            throw new InvalidPostException("Content must be between 10 and 500 characters");
        }
    }
}
