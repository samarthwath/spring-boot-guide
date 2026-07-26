package com.embarkx.blogapi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NewPostRepository extends JpaRepository<NewPost, UUID> {

    Optional<NewPost> findByTitleIgnoreCase(String title);
}
