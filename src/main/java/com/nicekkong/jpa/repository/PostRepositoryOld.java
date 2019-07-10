package com.nicekkong.jpa.repository;

import com.nicekkong.jpa.domain.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PostRepositoryOld {

    @PersistenceContext
    private EntityManager em;

    public Post add(Post post) {
        em.persist(post);
        return post;
    }

    public void delete(Post post) {
        em.remove(post);
    }

    public List<Post> findAll() {
        return em.createQuery("SELECT p FROM Post AS p").getResultList();
    }


}
