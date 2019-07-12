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
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @Rollback(false)
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

}
