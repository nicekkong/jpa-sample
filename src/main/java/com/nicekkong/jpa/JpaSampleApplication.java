package com.nicekkong.jpa;

import com.nicekkong.jpa.repository.MyRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = MyRepositoryImpl.class)
public class JpaSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaSampleApplication.class, args);
    }

}
