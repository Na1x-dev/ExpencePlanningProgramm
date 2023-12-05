package security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface SecurityService {
    boolean isAuthenticated();

    void configure(HttpSecurity http) throws Exception;

    void autoLogin(String username, String password);
}