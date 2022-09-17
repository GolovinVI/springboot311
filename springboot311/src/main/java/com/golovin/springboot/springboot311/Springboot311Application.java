package com.golovin.springboot.springboot311;

import com.golovin.springboot.springboot311.dao.RoleDao;
import com.golovin.springboot.springboot311.model.Role;
import com.golovin.springboot.springboot311.model.User;
import com.golovin.springboot.springboot311.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

@SpringBootApplication
public class Springboot311Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot311Application.class, args);
	}

	@Bean
	@Order(1)
	public CommandLineRunner initRole(RoleDao roleDao, UserService userService){
		return args->{
			Role role=new Role(1,"ROLE_ADMIN");
			role=roleDao.save(role);
			Role role1=new Role(2,"ROLE_USER");
			role1=roleDao.save(role1);
			User user=new User("admin","admin", Set.of(role));
			userService.saveUser(user);
			User user1=new User("user","user", Set.of(role1));
			userService.saveUser(user1);
		};
	}
}
