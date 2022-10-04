package com.golovin.springboot.springboot311.controller;

import com.golovin.springboot.springboot311.model.Role;
import com.golovin.springboot.springboot311.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<Set<Role>> list(){
        return ResponseEntity.ok(roleService.findAllRoles());
    }
}
