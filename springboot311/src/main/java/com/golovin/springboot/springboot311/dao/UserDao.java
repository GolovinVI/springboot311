package com.golovin.springboot.springboot311.dao;


import com.golovin.springboot.springboot311.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(Long id, User user);

    void deleteUser(Long id);

    User getUser(Long id);
}
