package security;

import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Configuration
@EnableWebSecurity
public class SecurityServiceImpl extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключаем CSRF защиту для API
                .authorizeRequests()
                .antMatchers("/api/login").permitAll() // Разрешаем доступ к эндпоинту логина без аутентификации
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/login") // URL, на который будет отправлен POST-запрос для аутентификации
                .successHandler((request, response, authentication) -> {
                    // Обработчик успешной аутентификации
                    // Здесь вы можете отправить клиенту какой-либо успешный ответ или выполнить другие действия
//                    response.setStatus(HttpStatus.OK.value());
                })
                .failureHandler((request, response, exception) -> {
                    // Обработчик неудачной аутентификации
                    // Здесь вы можете отправить клиенту сообщение об ошибке или выполнить другие действия
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout") // URL, на который будет отправлен POST-запрос для выхода из системы
                .logoutSuccessHandler((request, response, authentication) -> {
                    // Обработчик успешного выхода из системы
                    // Здесь вы можете отправить клиенту какой-либо успешный ответ или выполнить другие действия
//                    response.setStatus(HttpStatus.OK.value());
                })
                .permitAll();
    }

//    @Override
//    public void autoLogin(String username, String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        }
//    }
}
