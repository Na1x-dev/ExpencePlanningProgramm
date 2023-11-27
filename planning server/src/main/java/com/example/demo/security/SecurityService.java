package com.example.demo.security;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}