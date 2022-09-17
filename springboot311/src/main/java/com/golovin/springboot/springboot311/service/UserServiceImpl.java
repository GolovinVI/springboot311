package com.golovin.springboot.springboot311.service;

import com.golovin.springboot.springboot311.dao.RoleDao;
import com.golovin.springboot.springboot311.dao.UserDao;
import com.golovin.springboot.springboot311.model.Role;
import com.golovin.springboot.springboot311.model.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;


    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {
        user.setId(id);
        userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.findById(id).ifPresent(user->userDao.delete(user));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetails=getAllUsers()
                .stream().filter(user -> user.getFirstName().equals(username))
                .findFirst().orElseThrow(()->new UsernameNotFoundException("Not found: "+username));
        Set<Role> roleSet=roleDao.findAllRolesByUserId(userDetails.getId());
        userDetails.setRoles(roleSet);
        return userDetails;
    }
    public User findByFirstName(String firstName){
        return userDao.findByFirstName(firstName);
    }
}
