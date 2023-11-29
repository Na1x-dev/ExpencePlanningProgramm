package com.example.demo.repositories.user;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    User getByUserId(Long userId);
}
