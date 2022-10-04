package com.golovin.springboot.springboot311.controller;


import com.golovin.springboot.springboot311.model.User;
import com.golovin.springboot.springboot311.service.RoleService;
import com.golovin.springboot.springboot311.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final RoleService roleService;

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal User userData) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", userData.isAdmin());
        return "user/user";
    }


}
