package com.golovin.springboot.springboot311.service;

import com.golovin.springboot.springboot311.model.Role;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface RoleService {
    Set<Role> findAllRolesByUserId(Long userId);

    Set<Role> findAllRoles();
}
