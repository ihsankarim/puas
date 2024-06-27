package com.puas.clientapp.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .authorizeRequests(auth -> auth.antMatchers("/css/**", "/img/**", "/js/**").permitAll()
                                                .antMatchers("/", "/login/**", "/register").permitAll()
                                                .antMatchers("/dashboard/**").hasRole("ADMIN")
                                                .antMatchers("/history/**").hasAnyRole("USER", "ADMIN")
                                                .anyRequest()
                                                .authenticated())
                                .formLogin(login -> login.loginPage("/login")
                                                .loginProcessingUrl("/login")
                                                .successForwardUrl("/home")
                                                .failureForwardUrl("/login?error=true").permitAll())
                                .logout(logout -> logout.logoutUrl("/logout").permitAll()).build();
        }
}