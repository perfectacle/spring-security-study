package com.example.study;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebSecurityConfig {
    @Bean
    protected SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                .pathMatchers("/", "/home", "/login")
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .csrf()
                .disable()
                .logout()
                .and()
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new MapReactiveUserDetailsService(user);
    }
}
