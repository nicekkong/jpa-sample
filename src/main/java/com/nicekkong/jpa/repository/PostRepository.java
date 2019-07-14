package com.nicekkong.jpa.repository;

import com.nicekkong.jpa.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

}
