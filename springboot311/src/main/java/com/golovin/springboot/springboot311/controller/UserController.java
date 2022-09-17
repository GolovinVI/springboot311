package com.golovin.springboot.springboot311.controller;

import com.golovin.springboot.springboot311.model.Role;
import com.golovin.springboot.springboot311.model.User;
import com.golovin.springboot.springboot311.service.RoleService;
import com.golovin.springboot.springboot311.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.Set;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;


    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/index";
    }

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

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user, Model model) {
        Set<Role> roles=roleService.findAll();
        model.addAttribute("roleSet",roles);
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "user/new";

        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "user/edit";

        userService.updateUser(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }




}
