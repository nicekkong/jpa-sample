package com.nicekkong.jpa;


import com.nicekkong.jpa.domain.Comment;
import com.nicekkong.jpa.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void commentRepoTest() {
        Comment comment = new Comment();

        comment.setComment("Hello Comment");
        commentRepository.save(comment);

        List<Comment> comments  = commentRepository.findAll();
        assertThat(comments.size()).isEqualTo(1);
    }
}
