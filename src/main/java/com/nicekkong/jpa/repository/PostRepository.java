package com.nicekkong.jpa.repository;

import com.nicekkong.jpa.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContains(String title);
    List<Post> findByTitleContaining(String title);
}
