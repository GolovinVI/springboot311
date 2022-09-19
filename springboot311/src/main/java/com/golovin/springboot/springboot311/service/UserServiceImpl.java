package com.golovin.springboot.springboot311.service;

import com.golovin.springboot.springboot311.repository.RoleRepository;
import com.golovin.springboot.springboot311.repository.UserRepository;
import com.golovin.springboot.springboot311.model.Role;
import com.golovin.springboot.springboot311.model.User;
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
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleDao;


    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(user-> userRepository.delete(user));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
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
        return userRepository.findByFirstName(firstName);
    }
}
