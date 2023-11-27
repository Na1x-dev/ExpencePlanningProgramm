package com.example.demo.services.role;

import com.example.demo.models.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);

    List<Role> readAll();


    Role readByRoleName(String roleName);
}
