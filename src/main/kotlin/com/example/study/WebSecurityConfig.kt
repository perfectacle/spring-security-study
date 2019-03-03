package com.example.study

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class WebSecurityConfig {
    @Bean
    protected fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
                .authorizeExchange()
                .pathMatchers("/", "/home", "/login")
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .formLogin()
                .disable()
                .csrf()
                .disable()
                .httpBasic()
                .disable()
                .logout()
                .and()
                .build()
    }

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build()

        return MapReactiveUserDetailsService(user)
    }
}