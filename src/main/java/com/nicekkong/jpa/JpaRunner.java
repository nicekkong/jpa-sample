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


import com.nicekkong.jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional  // entityManager는 반드시 한 TR에서 이뤄져야 된다.
public class JpaRunner implements ApplicationRunner {


    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        postRepository.findAll().forEach(System.out::println);
        System.out.println("========================================");
        postRepository.findByTitleContains("JPA").forEach(System.out::println);
        System.out.println("========================================");
        postRepository.findByTitleContaining("JPA").forEach(System.out::println);
    }
}
