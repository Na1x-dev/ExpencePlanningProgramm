package com.example.server.services.role;

import com.example.server.models.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);

    List<Role> readAll();


    Role readByRoleName(String roleName);
}
