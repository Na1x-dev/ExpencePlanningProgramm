package com.example.server.services.role;

import com.example.server.models.Appeal;
import com.example.server.models.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);

    List<Role> readAll();
    Role read(Long roleId);

    Role readByRoleName(String roleName);
}
