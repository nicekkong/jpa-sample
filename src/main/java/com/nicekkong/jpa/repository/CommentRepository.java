package com.nicekkong.jpa.repository;

import com.nicekkong.jpa.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    List<Comment> findByCommentContainsIgnoreCase(String keyword);

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount, Sort sort);

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountDesc(String keyword, int likeCount);

    Page<Comment> findAll(Pageable pageable);

    Stream<Comment> findByCommentContains(String comment, Sort sort);

    // JDK 1.5 방식 , SpringApplication에 @EnableAsync 를 추가해주어야 한다.
    @Async
    Future<List<Comment>> findByCommentContains(String comment);

    @Async
    ListenableFuture<List<Comment>> findByCommentContainsIgnoreCase(String comment);

}
