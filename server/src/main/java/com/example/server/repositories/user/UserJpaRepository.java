package com.example.server.repositories.user;

import com.example.server.models.Role;
import com.example.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    User getByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
