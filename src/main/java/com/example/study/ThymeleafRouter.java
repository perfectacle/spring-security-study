package com.example.study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class ThymeleafRouter {
    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route(GET(""), request -> ok().render("home"))
                .andRoute(GET("/home"), request -> ok().render("home"))
                .andRoute(GET("/hello"), request -> ok().render("hello"))
                .andRoute(GET("/login"), request -> ok().render("login"));
    }
}