package com.example.server.repositories.role;

import com.example.server.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}