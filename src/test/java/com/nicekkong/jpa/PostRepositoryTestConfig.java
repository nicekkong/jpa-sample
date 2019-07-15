package com.nicekkong.jpa;

import com.nicekkong.jpa.domain.PostListner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {

    @Bean
    public PostListner postListner() {
        return new PostListner();
    }

}
