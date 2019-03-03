package com.example.study

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class LoginRouter(
        val loginHandler: LoginHandler
) {
    @Bean
    fun loginRoute() = router {
        accept(MediaType.APPLICATION_JSON_UTF8).nest {
            POST("/login", loginHandler::login)
        }
    }
}

