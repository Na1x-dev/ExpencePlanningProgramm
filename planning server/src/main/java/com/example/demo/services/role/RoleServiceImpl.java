package com.example.demo.services.role;

import com.example.demo.models.Role;
import com.example.demo.repositories.role.RoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleJpaRepository roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> readAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role readByRoleName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
