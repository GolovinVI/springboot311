package com.golovin.springboot.springboot311.controller;



import com.golovin.springboot.springboot311.model.Role;
import com.golovin.springboot.springboot311.model.User;
import com.golovin.springboot.springboot311.service.RoleService;
import com.golovin.springboot.springboot311.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userData=userService.findByFirstName(principalName);
        model.addAttribute("user",userData);
        model.addAttribute("roles",userData.getRoles());
        model.addAttribute("roleSet",roleService.findAll());
        return "user/index";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user, Model model) {
        Set<Role> roles=roleService.findAll();
        model.addAttribute("roleSet",roles);

        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        User userData=userService.findByFirstName(principalName);
        model.addAttribute("user",userData);
        model.addAttribute("roles",userData.getRoles());
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "user/new";

        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "user/edit";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute  User user,
                         @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
