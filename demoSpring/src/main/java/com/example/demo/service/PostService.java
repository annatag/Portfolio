package com.example.demo.service;

import com.example.demo.domain.Post;

import java.util.Optional;

public interface PostService {
    Iterable<Post> list();



    Post create(Post post);



    Optional<Post> read(long id);



//    Post update(long id, Post update);



    void deleteById(long id);
}
