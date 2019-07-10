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
import com.nicekkong.jpa.domain.Post;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional  // entityManager는 반드시 한 TR에서 이뤄져야 된다.
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager em;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        Session session = em.unwrap(Session.class);

        Post p1 = new Post();
        p1.setTitle("JPA Study");
//        session.save(p1);

        Comment c1 = new Comment();
        c1.setComment("열시미 해보아요~!!");

        Comment c2 = new Comment();
        c2.setComment("우리 모두 같이 해보아요");

        p1.addComment(c1);
        p1.addComment(c2);

        session.save(p1);

        // 1. JPQL
        TypedQuery<Post> q = em.createQuery("SELECT p FROM Post AS p", Post.class);
        List<Post> posts1 = q.getResultList();

        System.out.println("==1. JPQL ===========================");
        posts1.forEach(System.out::println);

        // 2. HQL
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Post> query = builder.createQuery(Post.class);

        Root<Post> root = query.from(Post.class);
        query.select(root);

        List<Post> posts2 = em.createQuery(query).getResultList();

        System.out.println("==2. HQL ===========================");
        posts2.forEach(System.out::println);

        // 3. Native Query
        List<Post> posts3 = em.createNativeQuery("select * from post", Post.class).getResultList();

        System.out.println("==3. Native Query ===========================");
        posts3.forEach(System.out::println);


    }
}
