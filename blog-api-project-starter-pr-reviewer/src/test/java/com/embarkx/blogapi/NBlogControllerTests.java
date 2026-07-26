package com.embarkx.blogapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class NBlogControllerTests {

    @Mock
    private NBlogService nBlogService;

    @InjectMocks
    private NBlogController nBlogController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(nBlogController).build();
    }

    // ==================== CREATE POST ====================

    @Test
    void createPost_returnsOk_whenValid() throws Exception {
        mockMvc.perform(post("/api/v1/posts")
                        .param("title", "Title")
                        .param("content", "Some valid content"))
                .andExpect(status().isOk())
                .andExpect(content().string("Post created"));

        verify(nBlogService, times(1)).createPost("Title", "Some valid content");
    }

    @Test
    void createPost_returnsBadRequest_whenInvalid() throws Exception {
        doThrow(new InvalidPostException("Content must be between 10 and 500 characters"))
                .when(nBlogService).createPost("Title", "short");

        mockMvc.perform(post("/api/v1/posts")
                        .param("title", "Title")
                        .param("content", "short"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Content must be between 10 and 500 characters"));
    }

    // ==================== GET ALL POSTS ====================

    @Test
    void getAllPosts_returnsNoContent_whenEmpty() throws Exception {
        when(nBlogService.getAllPosts()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllPosts_returnsOkWithList_whenNotEmpty() throws Exception {
        NewPost post = new NewPost("Title1", "Content for post one.");
        when(nBlogService.getAllPosts()).thenReturn(List.of(post));

        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[0].content").value("Content for post one."));
    }

    // ==================== SEARCH POST BY TITLE ====================

    @Test
    void searchPostByTitle_returnsOk_whenFound() throws Exception {
        NewPost post = new NewPost("Found Title", "Some content here.");
        when(nBlogService.searchPostByTitle("Found Title")).thenReturn(post);

        mockMvc.perform(get("/api/v1/posts/search").param("title", "Found Title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Found Title"));
    }

    @Test
    void searchPostByTitle_returnsNotFound_whenMissing() throws Exception {
        when(nBlogService.searchPostByTitle("Missing"))
                .thenThrow(new PostNotFoundException("Post not found for title Missing"));

        mockMvc.perform(get("/api/v1/posts/search").param("title", "Missing"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Post not found for title Missing"));
    }

    // ==================== GET POST BY ID ====================

    @Test
    void getPost_returnsOk_whenFound() throws Exception {
        UUID id = UUID.randomUUID();
        NewPost post = new NewPost("Title", "Content here for test.");
        when(nBlogService.getPostById(id)).thenReturn(post);

        mockMvc.perform(get("/api/v1/posts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title"));
    }

    @Test
    void getPost_returnsNotFound_whenMissing() throws Exception {
        UUID id = UUID.randomUUID();
        when(nBlogService.getPostById(id))
                .thenThrow(new PostNotFoundException("Post not found for id " + id));

        mockMvc.perform(get("/api/v1/posts/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Post not found for id " + id));
    }

    // ==================== VALIDATE CONTENT ====================

    @Test
    void validateContent_returnsOk_whenValid() throws Exception {
        mockMvc.perform(post("/api/v1/posts/validate").param("content", "short content"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));

        verify(nBlogService, times(1)).validateContentLength("short content");
    }

    @Test
    void validateContent_returnsBadRequest_whenTooLong() throws Exception {
        String longContent = "a".repeat(5001);
        doThrow(new InvalidPostException("Too long"))
                .when(nBlogService).validateContentLength(longContent);

        mockMvc.perform(post("/api/v1/posts/validate").param("content", longContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Too long"));
    }

    // ==================== DELETE POST ====================

    @Test
    void deletePost_returnsOk_whenFound() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/v1/posts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));

        verify(nBlogService, times(1)).deletePost(id);
    }

    @Test
    void deletePost_returnsNotFound_whenMissing() throws Exception {
        UUID id = UUID.randomUUID();
        doThrow(new PostNotFoundException("Post not found for id " + id))
                .when(nBlogService).deletePost(id);

        mockMvc.perform(delete("/api/v1/posts/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Post not found for id " + id));
    }

    // ==================== TOTAL WORD COUNT ====================

    @Test
    void getTotalWordCount_returnsExpectedString() throws Exception {
        mockMvc.perform(get("/api/v1/posts/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("Total words: 100200300"));
    }
}
