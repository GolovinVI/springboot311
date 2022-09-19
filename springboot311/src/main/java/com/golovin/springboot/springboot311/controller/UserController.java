package com.golovin.springboot.springboot311.controller;

import com.golovin.springboot.springboot311.model.Role;
import com.golovin.springboot.springboot311.model.User;
import com.golovin.springboot.springboot311.service.RoleService;
import com.golovin.springboot.springboot311.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.getUser(id);
        model.addAttribute("user", user);
        User userData=userService.findByFirstName(principalName);
        boolean isAdmin= userData.getRoles().stream()
                .map(Role::getAuthority)
                .anyMatch(name -> name.equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin",isAdmin);
        return "user/show";
    }






}
