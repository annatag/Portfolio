package com.security.demo.service;

import com.security.demo.domain.User;

public interface UserService {

    public User findByEmail(String email);
}
