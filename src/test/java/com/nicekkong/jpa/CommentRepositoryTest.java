package com.nicekkong.jpa;


import com.nicekkong.jpa.domain.Comment;
import com.nicekkong.jpa.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void commentRepoTest() {

        Comment comment = new Comment();
        comment.setComment("Spring Data JPA");
        comment.setLikeCount(1);

        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("spring");

        assertThat(comments.size()).isEqualTo(1);

    }

    @Test
    public void commentQueryTest2() {

        Comment comment = new Comment();
        comment.setComment("Spring Data JPA");
        comment.setLikeCount(20);

        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("spring", 10,
                Sort.by(Sort.Direction.DESC, "likeCount", "comment"));

        assertThat(comments.size()).isEqualTo(1);

    }


    @Test
    public void commentQueryTest2_2() {

        Comment comment = new Comment();
        comment.setComment("Spring Data JPA");
        comment.setLikeCount(20);

        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("spring", 10,
                Sort.by(Sort.Order.desc("likeCount"), Sort.Order.asc("comment")));

        assertThat(comments.size()).isEqualTo(1);

    }


    @Test
    public void commentQueryTest3() {

        createComment("Spring JPA", 1000);
        createComment("Spring JPA Study", 500);

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThanOrderByLikeCountDesc("spring", 10);

        assertThat(comments.size()).isEqualTo(2);
        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 1000);
        assertThat(comments).first().hasFieldOrPropertyWithValue("comment", "Spring JPA");
//        assertThat(comments.get(0).getComment()).isEqualTo("Spring");

    }

    @Test
    public void commentQueryTest4() {

        IntStream.range(0, 54).forEach(n ->
           createComment("Comment!!! " + n, n * 5)
        );

        Page<Comment> comments = commentRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount")));

        System.err.println("=START===============================");
        System.out.println(comments.getTotalPages());
        comments.getContent().forEach(c -> System.out.println(c.getComment()));
        System.err.println("==END================================");
    }

    @Test
    public void commentQueryTest5() {

        IntStream.range(1, 54).forEach(n ->
                createComment("Comment!!! " + n, n * 5)
        );

        PageRequest page = PageRequest.of(1, 10, Sort.by(Sort.Order.desc("likeCount")));

        Sort sort = Sort.by(Sort.Order.desc("likeCount"), Sort.Order.asc("comment"));

        try(Stream<Comment> commentStream = commentRepository.findByCommentContains("Comment", sort)) {
            Comment c1 = commentStream.findFirst().get();
            System.out.println("======>" + c1.getLikeCount());
            assertThat(c1.getLikeCount()).isEqualTo(265);
        }
    }
    
    private Comment createComment(String commentContent, int likeCount) {

        Comment comment = new Comment();
        comment.setComment(commentContent);
        comment.setLikeCount(likeCount);

        return commentRepository.save(comment);


    }








}
