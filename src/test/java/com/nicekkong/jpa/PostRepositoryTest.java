package com.nicekkong.jpa;


import com.nicekkong.jpa.domain.Post;
import com.nicekkong.jpa.domain.PostPublishedEvent;
import com.nicekkong.jpa.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(PostRepositoryTestConfig.class)
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void eventTest() {

        Post post = new Post();
        post.setTitle("Event Driven JPA");
        PostPublishedEvent event = new PostPublishedEvent(post);
        ctx.publishEvent(event);
    }

    @Test
    public void crud() {
        Post post = new Post();
        post.setTitle("hibernate");

        assertThat(postRepository.contains(post)).isFalse();

        postRepository.save(post);

        assertThat(postRepository.contains(post)).isTrue();

        postRepository.delete(post);
        postRepository.flush();

    }

}
