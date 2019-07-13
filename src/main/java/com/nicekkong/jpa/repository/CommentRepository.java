package com.nicekkong.jpa.repository;

import com.nicekkong.jpa.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCommentContainsIgnoreCase(String keyword);

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount, Sort sort);

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountDesc(String keyword, int likeCount);

    Page<Comment> findAll(Pageable pageable);

    Stream<Comment> findByCommentContains(String comment, Sort sort);

}
