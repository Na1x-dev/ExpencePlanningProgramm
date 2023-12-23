package com.example.server.services.user;

import com.example.server.models.Appeal;
import com.example.server.models.Role;
import com.example.server.models.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserService {
    void create(User user);

    User findByUserName(String userName);

    List<User> readAll();

    boolean update( Long userId, User user);

    boolean delete(Long userId);
    User readById(Long userId);

    User read(Long userId);
}
