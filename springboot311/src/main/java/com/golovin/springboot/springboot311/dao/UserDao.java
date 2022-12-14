package com.golovin.springboot.springboot311.dao;


import com.golovin.springboot.springboot311.model.Role;
import com.golovin.springboot.springboot311.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Set;

@Repository
public interface UserDao extends CrudRepository<User,Long> {

    List<User> findAll();

    User findByFirstName(String firstName);
}
