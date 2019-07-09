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
import com.nicekkong.jpa.domain.Study;
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

        Study study = new Study();
        study.setName("Spring Data JPA");

//        study.setOwner(account);
//        account.getStudies().add(study);

        // 위의 두줄을 convenient 메서드를 통해 일괄로 작업
        account.addStudy(study);

        // Hibernate Session 객체 사용
        Session session = em.unwrap(Session.class);
        session.save(account);
        session.save(study);
    }
}
