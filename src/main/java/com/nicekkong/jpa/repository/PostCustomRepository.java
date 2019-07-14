package com.nicekkong.jpa.repository;

import com.nicekkong.jpa.domain.Post;

import java.util.List;

public interface PostCustomRepository {

    List<Post> findMyPost();
}
