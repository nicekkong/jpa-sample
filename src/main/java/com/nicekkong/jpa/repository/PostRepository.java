package com.nicekkong.jpa.repository;

import com.nicekkong.jpa.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContains(String title);
    List<Post> findByTitleContaining(String title);

    Page<Post> findByTitleContains(String title, Pageable pageable);

    Page<Post> findByTitleContainsIgnoreCase(String title, Pageable pageable);

    long countByTitleContains(String title);

    void deletePostById(Long id);

    void deleteById(Long id);

    void removePostById(Long id);


}
