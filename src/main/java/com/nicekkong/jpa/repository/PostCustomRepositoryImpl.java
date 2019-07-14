package com.nicekkong.jpa.repository;

import com.nicekkong.jpa.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@Transactional
public class PostCustomRepositoryImpl implements PostCustomRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<Post> findMyPost() {

        return em.createQuery("SELECT p From Post as p", Post.class)
                .getResultList();
    }
}
