package com.golovin.springboot.springboot311.repository;


import com.golovin.springboot.springboot311.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    User findByFirstName(String firstName);
}
