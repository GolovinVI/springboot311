package com.golovin.springboot.springboot311.service;


import com.golovin.springboot.springboot311.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
    User getUser(Long id);
}
