package com.nicekkong.jpa;

import com.nicekkong.jpa.domain.Post;
import com.nicekkong.jpa.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest  // Data layer 만 Slicing Test를 한다. // Repository 관련 Bean만 등록이 된다.
@Rollback(false)
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void crudRepositoryTest() {
        // Given
        Post post = new Post();
        post.setTitle("Hellow Spring boot Data Common");
        assertThat(post.getId()).isNull();

        // When
        Post newPost = postRepository.save(post);

        // Then
        assertThat(newPost.getId()).isNotNull();

        List<Post> posts = postRepository.findAll();
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        // when
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10) );
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        Page<Post> jpaPage = postRepository.findByTitleContainsIgnoreCase("Spring", PageRequest.of(0, 10));
        assertThat(jpaPage.getTotalElements()).isEqualTo(1);


        postRepository.deletePostById(1L);
//        postRepository.deleteById(1L);
//        postRepository.removePostById(1L);    // 세가지 모두 동일한 DELETE 쿼리를 날린다.


    }


    @Test
    public void postSaveTest() {

        Post p1 = Post.builder().title("Spring Data").build();
        Post p2 = Post.builder().title("Spring Summer").build();

        postRepository.save(p1);
        postRepository.save(p2);


        System.out.println("START==============================================");


        List<Post> posts = postRepository.findByTitleContains("Spring");
        posts.forEach(p -> System.out.println(p.getTitle()));


//        postRepository.findById(1L).ifPresent(p -> System.out.println(p.getTitle()));
        System.out.println("END================================================");

    }


    @Test
    public void postQueryTest() {

        Post p1 = Post.builder().title("Spring Data").build();
        Post p2 = Post.builder().title("Spring Summer").build();

        postRepository.save(p1);
        postRepository.save(p2);

        postRepository.findAll();

//        p1.setTitle("spring_data");
//
//        Post p3 = Post.builder().id(3L).title("spring_summer").build();
//
//        postRepository.save(p3);

        System.out.println("=============COUNT==============");
        System.out.println("=====>" + postRepository.count());
    }


    @Test
    public void findAllTest() {
        System.out.println("==========================================================");
        postRepository.findAll().forEach(p -> System.out.println(p.getTitle()));
    }
}
