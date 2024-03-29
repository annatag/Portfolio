package com.security.demo.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "roles" )
public class Role {

    @Id
    @GeneratedValue
    private Long id;



    private String role;



    @ManyToMany( mappedBy = "roles")
    private Set<User> users = new HashSet<User>();



    public Role(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
