package com.security.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {


    @RequestMapping("/posts")
    public String list(){
        return "list of the posts";
    }


    @RequestMapping("/drafts")
    public String viewDrafts(){
        return "list of the drafts";
    }


    @RequestMapping("admin/posts/create")
    public String add(){

        return "content has been added";
    }
}
