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


import com.nicekkong.jpa.domain.Account;
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
        Account account = new Account();
        account.setUsername("nicekkong2");
        account.setPassword("닉과르");

        em.persist(account);

        // Hibernate Session 객체 사용
        Session session = em.unwrap(Session.class);
        session.save(account);
    }
}
