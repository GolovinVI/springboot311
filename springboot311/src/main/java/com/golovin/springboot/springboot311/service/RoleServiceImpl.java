package com.golovin.springboot.springboot311.service;

import com.golovin.springboot.springboot311.dao.RoleDao;
import com.golovin.springboot.springboot311.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleDao roleDao;
    @Override
    public Set<Role> findAllRolesByUserId(Long userId) {
        return roleDao.findAllRolesByUserId(userId);
    }

    @Override
    public Set<Role> findAll() {
        return roleDao.findAll();
    }
}
