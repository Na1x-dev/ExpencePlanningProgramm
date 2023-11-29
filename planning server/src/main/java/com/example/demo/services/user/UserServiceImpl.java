package com.example.demo.services.user;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.role.RoleJpaRepository;
import com.example.demo.repositories.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserJpaRepository userRepository;
    @Autowired
    private RoleJpaRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setRoles(new HashSet<>(roleRepository.findAll()));
//        user.addRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);

    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean update(User user, Long id) {
        if (userRepository.existsById(id)) {
            user.setUserId(id);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User readById(Long userId) {
        return userRepository.getByUserId(userId);
    }


}
