package com.security.demo.repository;

import com.security.demo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
