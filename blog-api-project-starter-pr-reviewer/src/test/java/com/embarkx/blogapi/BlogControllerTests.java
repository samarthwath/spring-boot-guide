//package com.embarkx.blogapi;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.lang.reflect.Field;
//import java.util.List;
//
//import static org.hamcrest.Matchers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//class BlogControllerTests {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        // Initialize MockMvc using WebApplicationContext
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        
//        // Reset the static posts list before each test
//        Field postsField = BlogController.class.getDeclaredField("posts");
//        postsField.setAccessible(true);
//        @SuppressWarnings("unchecked")
//        List<String> posts = (List<String>) postsField.get(null);
//        posts.clear();
//    }
//
//    // ==================== CREATE POST TESTS ====================
//
//    @Test
//    void testCreatePost_Success() throws Exception {
//        String requestBody = "{\"title\": \"My First Post\", \"content\": \"This is the content for my first post.\"}";
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Post created"));
//    }
//
//    @Test
//    void testCreatePost_WithValidBoundaryContent() throws Exception {
//        // Content exactly 10 characters (minimum valid length)
//        String requestBody = "{\"title\": \"Test\", \"content\": \"0123456789\"}";
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Post created"));
//    }
//
//    @Test
//    void testCreatePost_WithMaximumContent() throws Exception {
//        // Content exactly 500 characters (maximum valid length)
//        String content = "a".repeat(500);
//        String requestBody = String.format("{\"title\": \"Test\", \"content\": \"%s\"}", content);
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Post created"));
//    }
//
//    @Test
//    void testCreatePost_EmptyTitle() throws Exception {
//        String requestBody = "{\"title\": \"\", \"content\": \"This is valid content length.\"}";
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Title and content must not be empty"));
//    }
//
//    @Test
//    void testCreatePost_BlankTitle() throws Exception {
//        String requestBody = "{\"title\": \"   \", \"content\": \"This is valid content length.\"}";
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Title and content must not be empty"));
//    }
//
//    @Test
//    void testCreatePost_NullTitle() throws Exception {
//        String requestBody = "{\"title\": null, \"content\": \"This is valid content length.\"}";
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Title and content must not be empty"));
//    }
//
//    @Test
//    void testCreatePost_EmptyContent() throws Exception {
//        String requestBody = "{\"title\": \"Valid Title\", \"content\": \"\"}";
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Title and content must not be empty"));
//    }
//
//    @Test
//    void testCreatePost_BlankContent() throws Exception {
//        String requestBody = "{\"title\": \"Valid Title\", \"content\": \"   \"}";
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Title and content must not be empty"));
//    }
//
//    @Test
//    void testCreatePost_NullContent() throws Exception {
//        String requestBody = "{\"title\": \"Valid Title\", \"content\": null}";
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Title and content must not be empty"));
//    }
//
//    @Test
//    void testCreatePost_ContentTooShort() throws Exception {
//        String requestBody = "{\"title\": \"Title\", \"content\": \"short\"}"; // 5 chars, needs at least 10
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Content must be between 10 and 500 characters"));
//    }
//
//    @Test
//    void testCreatePost_ContentTooLong() throws Exception {
//        String content = "a".repeat(501); // 501 chars, exceeds max 500
//        String requestBody = String.format("{\"title\": \"Title\", \"content\": \"%s\"}", content);
//
//        mockMvc.perform(post("/api/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Content must be between 10 and 500 characters"));
//    }
//
//    // ==================== GET ALL POSTS TESTS ====================
//
//    @Test
//    void testGetAllPosts_Empty() throws Exception {
//        mockMvc.perform(get("/api/posts"))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void testGetAllPosts_WithMultiplePosts() throws Exception {
//        // Create multiple posts first
//        String post1 = "{\"title\": \"Post 1\", \"content\": \"Content for post 1.\"}";
//        String post2 = "{\"title\": \"Post 2\", \"content\": \"Content for post 2.\"}";
//        String post3 = "{\"title\": \"Post 3\", \"content\": \"Content for post 3.\"}";
//
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post1))
//                .andExpect(status().isOk());
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post2))
//                .andExpect(status().isOk());
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post3))
//                .andExpect(status().isOk());
//
//        // Get all posts
//        mockMvc.perform(get("/api/posts"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(jsonPath("$[0]", containsString("Post 1")))
//                .andExpect(jsonPath("$[1]", containsString("Post 2")))
//                .andExpect(jsonPath("$[2]", containsString("Post 3")));
//    }
//
//    @Test
//    void testGetAllPosts_ReturnsList() throws Exception {
//        // Create one post
//        String post = "{\"title\": \"Single Post\", \"content\": \"This is a single post.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Get all posts and verify it returns a list
//        mockMvc.perform(get("/api/posts"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", isA(java.util.List.class)))
//                .andExpect(jsonPath("$", hasSize(1)));
//    }
//
//    // ==================== GET POST BY ID TESTS ====================
//
//    @Test
//    void testGetPost_Success() throws Exception {
//        // Create a post first
//        String post = "{\"title\": \"Test Post\", \"content\": \"This is a test post content.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Get the post
//        mockMvc.perform(get("/api/posts/0"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Test Post")))
//                .andExpect(content().string(containsString("This is a test post content.")));
//    }
//
//    @Test
//    void testGetPost_ValidFormat() throws Exception {
//        String post = "{\"title\": \"Sample\", \"content\": \"This is sample content here.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get("/api/posts/0"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Sample:This is sample content here."));
//    }
//
//    @Test
//    void testGetPost_NotFound() throws Exception {
//        mockMvc.perform(get("/api/posts/999"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Post not found for id 999"));
//    }
//
//    @Test
//    void testGetPost_NegativeId() throws Exception {
//        mockMvc.perform(get("/api/posts/-1"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Post not found for id -1"));
//    }
//
//    @Test
//    void testGetPost_IdZeroWhenEmpty() throws Exception {
//        mockMvc.perform(get("/api/posts/0"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Post not found for id 0"));
//    }
//
//    @Test
//    void testGetPost_MultiplePostsGetSecond() throws Exception {
//        String post1 = "{\"title\": \"Post 1\", \"content\": \"Content for post one.\"}";
//        String post2 = "{\"title\": \"Post 2\", \"content\": \"Content for post two.\"}";
//
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post1))
//                .andExpect(status().isOk());
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post2))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get("/api/posts/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Post 2")));
//    }
//
//    // ==================== VALIDATE CONTENT TESTS ====================
//
//    @Test
//    void testValidateContent_Success() throws Exception {
//        mockMvc.perform(post("/api/posts/validate")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("content", "This is valid content."))
//                .andExpect(status().isOk())
//                .andExpect(content().string("OK"));
//    }
//
//    @Test
//    void testValidateContent_EmptyContent() throws Exception {
//        mockMvc.perform(post("/api/posts/validate")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("content", ""))
//                .andExpect(status().isOk())
//                .andExpect(content().string("OK"));
//    }
//
//    @Test
//    void testValidateContent_LongContent() throws Exception {
//        String longContent = "a".repeat(5001); // Exceeds max-length of 5000
//        mockMvc.perform(post("/api/posts/validate")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("content", longContent))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Too long"));
//    }
//
//    @Test
//    void testValidateContent_WithSpecialCharacters() throws Exception {
//        String content = "This contains !@#$%^&*() special characters.";
//        mockMvc.perform(post("/api/posts/validate")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("content", content))
//                .andExpect(status().isOk())
//                .andExpect(content().string("OK"));
//    }
//
//    // ==================== DELETE POST TESTS ====================
//
//    @Test
//    void testDeletePost_Success() throws Exception {
//        // Create a post
//        String post = "{\"title\": \"Post to Delete\", \"content\": \"This post will be deleted.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Verify it exists
//        mockMvc.perform(get("/api/posts/0"))
//                .andExpect(status().isOk());
//
//        // Delete it
//        mockMvc.perform(delete("/api/posts/0"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Deleted"));
//
//        // Verify it's gone
//        mockMvc.perform(get("/api/posts/0"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testDeletePost_NotFound() throws Exception {
//        mockMvc.perform(delete("/api/posts/999"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Post not found for id 999"));
//    }
//
//    @Test
//    void testDeletePost_NegativeId() throws Exception {
//        mockMvc.perform(delete("/api/posts/-1"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Post not found for id -1"));
//    }
//
//    @Test
//    void testDeletePost_ShiftsIdsAfterDeletion() throws Exception {
//        // Create three posts
//        String post1 = "{\"title\": \"Post 1\", \"content\": \"Content for post one.\"}";
//        String post2 = "{\"title\": \"Post 2\", \"content\": \"Content for post two.\"}";
//        String post3 = "{\"title\": \"Post 3\", \"content\": \"Content for post three.\"}";
//
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post1))
//                .andExpect(status().isOk());
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post2))
//                .andExpect(status().isOk());
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post3))
//                .andExpect(status().isOk());
//
//        // Delete the second post
//        mockMvc.perform(delete("/api/posts/1"))
//                .andExpect(status().isOk());
//
//        // Verify Post 3 is now at index 1
//        mockMvc.perform(get("/api/posts/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Post 3")));
//
//        // Verify Post 2 is gone from index 1
//        mockMvc.perform(get("/api/posts/2"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testDeletePost_DeleteMultiplePosts() throws Exception {
//        // Create two posts
//        String post1 = "{\"title\": \"Post 1\", \"content\": \"Content for post one.\"}";
//        String post2 = "{\"title\": \"Post 2\", \"content\": \"Content for post two.\"}";
//
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post1))
//                .andExpect(status().isOk());
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post2))
//                .andExpect(status().isOk());
//
//        // Delete both
//        mockMvc.perform(delete("/api/posts/0")).andExpect(status().isOk());
//        mockMvc.perform(delete("/api/posts/0")).andExpect(status().isOk());
//
//        // List should be empty
//        mockMvc.perform(get("/api/posts"))
//                .andExpect(status().isNoContent());
//    }
//
//    // ==================== UPDATE POST TESTS ====================
//
//    @Test
//    void testUpdatePost_Success() throws Exception {
//        // Create a post
//        String post = "{\"title\": \"Original Title\", \"content\": \"Original content here.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Update it
//        String updateRequest = "{\"title\": \"Updated Title\", \"content\": \"Updated content here.\"}";
//        mockMvc.perform(put("/api/posts/0")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Post updated"));
//
//        // Verify the update
//        mockMvc.perform(get("/api/posts/0"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Updated Title")))
//                .andExpect(content().string(containsString("Updated content here.")));
//    }
//
//    @Test
//    void testUpdatePost_NotFound() throws Exception {
//        String updateRequest = "{\"title\": \"Title\", \"content\": \"Content here.\"}";
//        mockMvc.perform(put("/api/posts/999")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Post not found for id 999"));
//    }
//
//    @Test
//    void testUpdatePost_NegativeId() throws Exception {
//        String updateRequest = "{\"title\": \"Title\", \"content\": \"Content here.\"}";
//        mockMvc.perform(put("/api/posts/-1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Post not found for id -1"));
//    }
//
//    @Test
//    void testUpdatePost_EmptyTitle() throws Exception {
//        // Create a post first
//        String post = "{\"title\": \"Original Title\", \"content\": \"Original content here.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Try to update with empty title
//        String updateRequest = "{\"title\": \"\", \"content\": \"Updated content here.\"}";
//        mockMvc.perform(put("/api/posts/0")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Title and content must not be empty"));
//    }
//
//    @Test
//    void testUpdatePost_BlankContent() throws Exception {
//        // Create a post first
//        String post = "{\"title\": \"Original Title\", \"content\": \"Original content here.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Try to update with blank content
//        String updateRequest = "{\"title\": \"New Title\", \"content\": \"   \"}";
//        mockMvc.perform(put("/api/posts/0")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Title and content must not be empty"));
//    }
//
//    @Test
//    void testUpdatePost_ContentTooShort() throws Exception {
//        // Create a post first
//        String post = "{\"title\": \"Original Title\", \"content\": \"Original content here.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Try to update with content too short
//        String updateRequest = "{\"title\": \"New Title\", \"content\": \"short\"}";
//        mockMvc.perform(put("/api/posts/0")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Content must be between 10 and 500 characters"));
//    }
//
//    @Test
//    void testUpdatePost_ContentTooLong() throws Exception {
//        // Create a post first
//        String post = "{\"title\": \"Original Title\", \"content\": \"Original content here.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Try to update with content too long
//        String longContent = "a".repeat(501);
//        String updateRequest = String.format("{\"title\": \"New Title\", \"content\": \"%s\"}", longContent);
//        mockMvc.perform(put("/api/posts/0")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Content must be between 10 and 500 characters"));
//    }
//
//    @Test
//    void testUpdatePost_ValidBoundaryContent() throws Exception {
//        // Create a post first
//        String post = "{\"title\": \"Original Title\", \"content\": \"Original content here.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Update with exactly 10 characters (minimum valid)
//        String updateRequest = "{\"title\": \"New Title\", \"content\": \"0123456789\"}";
//        mockMvc.perform(put("/api/posts/0")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Post updated"));
//
//        // Update with exactly 500 characters (maximum valid)
//        String maxContent = "b".repeat(500);
//        String updateRequest2 = String.format("{\"title\": \"New Title 2\", \"content\": \"%s\"}", maxContent);
//        mockMvc.perform(put("/api/posts/0")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest2))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Post updated"));
//    }
//
//    @Test
//    void testUpdatePost_MultiplePostsUpdateSecond() throws Exception {
//        // Create two posts
//        String post1 = "{\"title\": \"Post 1\", \"content\": \"Content for post one.\"}";
//        String post2 = "{\"title\": \"Post 2\", \"content\": \"Content for post two.\"}";
//
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post1))
//                .andExpect(status().isOk());
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post2))
//                .andExpect(status().isOk());
//
//        // Update second post
//        String updateRequest = "{\"title\": \"Updated Post 2\", \"content\": \"Updated content two.\"}";
//        mockMvc.perform(put("/api/posts/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isOk());
//
//        // Verify the update
//        mockMvc.perform(get("/api/posts/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Updated Post 2")));
//
//        // Verify first post is unchanged
//        mockMvc.perform(get("/api/posts/0"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Post 1")));
//    }
//
//    // ==================== GET TOTAL WORD COUNT TESTS ====================
//
//    @Test
//    void testGetTotalWordCount() throws Exception {
//        mockMvc.perform(get("/api/posts/total"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Total words: 600"));
//    }
//
//    // ==================== INTEGRATION TESTS ====================
//
//    @Test
//    void testCreateGetDeleteWorkflow() throws Exception {
//        // Create
//        String post = "{\"title\": \"Integration Test\", \"content\": \"This is an integration test.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Get all and verify count
//        mockMvc.perform(get("/api/posts"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//
//        // Get by id
//        mockMvc.perform(get("/api/posts/0"))
//                .andExpect(status().isOk());
//
//        // Delete
//        mockMvc.perform(delete("/api/posts/0"))
//                .andExpect(status().isOk());
//
//        // Verify empty
//        mockMvc.perform(get("/api/posts"))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void testCreateUpdateDeleteWorkflow() throws Exception {
//        // Create
//        String post = "{\"title\": \"Workflow Test\", \"content\": \"Original workflow content.\"}";
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post))
//                .andExpect(status().isOk());
//
//        // Update
//        String updateRequest = "{\"title\": \"Updated Workflow\", \"content\": \"Updated workflow content.\"}";
//        mockMvc.perform(put("/api/posts/0")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isOk());
//
//        // Verify updated
//        mockMvc.perform(get("/api/posts/0"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Updated Workflow")));
//
//        // Delete
//        mockMvc.perform(delete("/api/posts/0"))
//                .andExpect(status().isOk());
//
//        // Verify deleted
//        mockMvc.perform(get("/api/posts/0"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testMultiplePostOperations() throws Exception {
//        // Create 3 posts
//        String post1 = "{\"title\": \"Post A\", \"content\": \"Content A here now.\"}";
//        String post2 = "{\"title\": \"Post B\", \"content\": \"Content B here now.\"}";
//        String post3 = "{\"title\": \"Post C\", \"content\": \"Content C here now.\"}";
//
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post1))
//                .andExpect(status().isOk());
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post2))
//                .andExpect(status().isOk());
//        mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content(post3))
//                .andExpect(status().isOk());
//
//        // Verify all 3 exist
//        mockMvc.perform(get("/api/posts"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)));
//
//        // Update post 2
//        String updateRequest = "{\"title\": \"Post B Updated\", \"content\": \"Updated content B now.\"}";
//        mockMvc.perform(put("/api/posts/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateRequest))
//                .andExpect(status().isOk());
//
//        // Delete post 1
//        mockMvc.perform(delete("/api/posts/1"))
//                .andExpect(status().isOk());
//
//        // Verify 2 remain and post 3 is now at index 1
//        mockMvc.perform(get("/api/posts"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)));
//
//        mockMvc.perform(get("/api/posts/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Post C")));
//    }
//}
//
//
