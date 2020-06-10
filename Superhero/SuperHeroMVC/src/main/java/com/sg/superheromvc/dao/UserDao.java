/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superheromvc.dao;

import com.sg.superheromvc.model.User;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface UserDao {

    public User addUser(User newUser);

    public void deleteUser(String username);

    public List<User> getAllUsers();
    
    public User editUser(User oldUser);
    
    public Boolean enableUser(int status);

}
