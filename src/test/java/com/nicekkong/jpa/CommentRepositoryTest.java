package com.nicekkong.jpa;


import com.nicekkong.jpa.domain.Comment;
import com.nicekkong.jpa.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void asyncFutureTest() throws ExecutionException, InterruptedException, TimeoutException {

        this.createComment("Spring Data", 100);
        this.createComment("JPA for Spring", 200);

        // Future : JDK 1.5부터 사용됨
        Future<List<Comment>> future = commentRepository.findByCommentContains("Spring");
        System.out.println("============================================");
        System.out.println("is Done? " + future.isDone());


        List<Comment> comments = future.get();
//        List<Comment> comments = future.get(3, TimeUnit.SECONDS);
        comments.forEach(c -> System.out.println(c.getComment()));
    }


    @Test
    public void asyncListenableFutureTest() throws ExecutionException, InterruptedException, TimeoutException {

        this.createComment("Spring Data", 100);
        this.createComment("JPA for Spring", 200);

        // Future : JDK 1.5부터 사용됨
        ListenableFuture<List<Comment>> future = commentRepository.findByCommentContainsIgnoreCase("Spring");
        System.out.println("============================================");
        System.out.println("is Done? " + future.isDone());

        future.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable);
            }

            @Override
            public void onSuccess(List<Comment> comments) {
                comments.forEach(c -> System.out.println(c.getComment()));
            }
        });
    }





    private Comment createComment(String commentContent, int likeCount) {

        Comment comment = new Comment();
        comment.setComment(commentContent);
        comment.setLikeCount(likeCount);

        return commentRepository.save(comment);


    }








}
