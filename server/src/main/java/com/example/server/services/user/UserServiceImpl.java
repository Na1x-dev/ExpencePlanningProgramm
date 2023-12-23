package com.example.server.services.user;

import com.example.server.models.Role;
import com.example.server.models.User;
import com.example.server.repositories.role.RoleJpaRepository;
import com.example.server.repositories.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserJpaRepository userRepository;
    @Autowired
    private RoleJpaRepository roleRepository;

    //    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void create(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
    public boolean update(Long userId, User user) {
        if (userRepository.existsByUserId(userId)) {
            user.setUserId(userId);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long userId) {
        if (userRepository.existsByUserId(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public User readById(Long userId) {
        return userRepository.getByUserId(userId);
    }

    @Override
    public User read(Long userId) {
        return userRepository.findByUserId(userId);
    }


}
