package com.golovin.springboot.springboot311.controller;

import com.golovin.springboot.springboot311.model.User;
import com.golovin.springboot.springboot311.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/principle")
    public ResponseEntity<User> getPrinciple(@AuthenticationPrincipal User userData){
        return ResponseEntity.ok(userData);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getData(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id,@RequestBody User user){
        userService.updateUser(id,user);
        return ResponseEntity.ok().build();
    }
}
