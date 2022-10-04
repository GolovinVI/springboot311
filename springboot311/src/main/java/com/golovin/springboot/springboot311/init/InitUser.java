package com.golovin.springboot.springboot311.init;


import com.golovin.springboot.springboot311.model.Role;
import com.golovin.springboot.springboot311.model.User;
import com.golovin.springboot.springboot311.repository.RoleRepository;
import com.golovin.springboot.springboot311.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Set;

@Configuration
public class InitUser {

    @Bean
    @Order(1)
    public CommandLineRunner initRole(RoleRepository roleDao, UserService userService) {
        return args -> {
            Role role = new Role(1, "ROLE_ADMIN");
            role = roleDao.save(role);
            Role role1 = new Role(2, "ROLE_USER");
            role1 = roleDao.save(role1);
            User user = new User("admin", "admin", Set.of(role));
            userService.saveUser(user);
            User user1 = new User("user", "user", Set.of(role1));
            userService.saveUser(user1);
        };
    }
}
