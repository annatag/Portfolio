package com.example.demo;

import com.example.demo.domain.Author;
import com.example.demo.domain.Post;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AuthorRepository authorRepository, PostRepository postRepository) {

        return args -> {



            Author dv = new Author("Dan","Vega","danvega@gmail.com");

            authorRepository.save( dv );



            Post post = new Post("Spring Boot Rocks!");

            post.setSlug("spring-data-rocks");

            post.setTeaser("Post Teaser");

            post.setBody("Post Body");

            post.setPostedOn(new Date());

            post.setAuthor(dv);

            postRepository.save(post);



            Post rest = new Post("REST is what all the cool kids are doing");

            rest.setSlug("rest-is-cool");

            rest.setTeaser("REST Teaser");

            rest.setBody("REST BODY");

            rest.setPostedOn(new Date());

            rest.setAuthor(dv);

            postRepository.save(rest);



        };

    }



}
