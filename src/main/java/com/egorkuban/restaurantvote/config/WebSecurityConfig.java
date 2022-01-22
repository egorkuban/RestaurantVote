package com.egorkuban.restaurantvote.config;

import com.egorkuban.restaurantvote.jpa.Role;
import com.egorkuban.restaurantvote.jpa.model.User;
import com.egorkuban.restaurantvote.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers( "/api/v1/admin/**").hasAuthority(Role.ADMIN.getAuthority())
                .antMatchers("/api/v1/user/**").hasAuthority(Role.USER.getAuthority())
                .anyRequest()
                .authenticated();
        http.httpBasic();
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return email -> {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
            return org.springframework.security.core.userdetails.User.builder()
                     .username(user.getEmail())
                     .password(passwordEncoder().encode(user.getPassword()))
                     .authorities(user.getRoles())
                     .build();
        };
    }
}
