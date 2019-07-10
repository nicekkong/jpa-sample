/*
 * Copyright (c) 2019.
 * nicekkong JE Foundation
 */

/******************************************************
 * Project Name : jpa04
 * File Name    : .java
 * Author       : nicekkong@gmail.com
 * Create Date  : 2019-07-08 23:57
 * Description  : 
 ******************************************************/

package com.nicekkong.jpa;


import com.nicekkong.jpa.domain.Comment;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional  // entityManager는 반드시 한 TR에서 이뤄져야 된다.
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager em;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        Post post = new Post();
//        post.setTitle("Spring Data JPA...");
//
//        Comment comment = new Comment();
//        comment.setComment("빨리 보고 싶어요~!!");
//
//        post.addComment(comment);
//
//        Comment comment1 = new Comment();
//        comment1.setComment("시작하자아아아~~");
//
//        post.addComment(comment1);

        Session session = em.unwrap(Session.class);


//        session.save(comment);        // Post에서 cascadeType.Persistent 로 설정하면 엔티티 상태 변화를 함께 전달한다.
//        session.save(comment1);

//        Post post1 = session.get(Post.class, 1L);
//        session.delete(post1);        // cascadeType.REMOVED에 의해 함께 삭제 된다.


//        Post post = session.get(Post.class, 1L);
//
//        System.out.println(post.getTitle());
//        post.getComments().forEach(c -> {
//            System.out.println("=================");
//            System.out.println(c.getComment());
//        });

        Comment comment = session.get(Comment.class, 2L);
        System.out.println(comment.getComment());
        System.out.println(comment.getPost().getTitle());


    }
}
