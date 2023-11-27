package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/signUpPage/index").permitAll()
                .antMatchers("/mainPage/index").access("hasRole('ROLE_USER')")
                .antMatchers("/clientsPage/index").access("hasRole('ROLE_USER')")
                .antMatchers("/newSalePage/index").access("hasRole('ROLE_USER')")
                .antMatchers("/newSupplyPage/index").access("hasRole('ROLE_USER')")
                .antMatchers("/salesPage/index").access("hasRole('ROLE_USER')")
                .antMatchers("/suppliesPage/index").access("hasRole('ROLE_USER')")
                .antMatchers("/sqlPage/index").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/userPage/index").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/newUserPage/index").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/logInPage/index")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/logInPage/index")
                .permitAll();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}