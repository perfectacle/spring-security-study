package com.example.study

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class ThymeleafRouter {
    @Bean
    fun route() = router {
        GET("") { ok().render("home") }
        GET("/home") { ok().render("home") }
        GET("/hello") { ok().render("hello") }
        GET("/login") { ok().render("login") }
    }
}