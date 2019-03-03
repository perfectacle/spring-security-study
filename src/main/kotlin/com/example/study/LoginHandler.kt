package com.example.study

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class LoginHandler(userDetailsService: ReactiveUserDetailsService) {
    private val authenticationManager: UserDetailsRepositoryReactiveAuthenticationManager = UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService)
    private val securityContextRepository: ServerSecurityContextRepository = NoOpServerSecurityContextRepository.getInstance()

    fun login(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(LoginRequest::class.java)
              .flatMap { loginRequest ->
                  authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.username,
                                                                                         loginRequest.password))
              }
              .flatMap {
                  securityContextRepository.save(ServerWebExchange, SecurityContextImpl())
                  ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(emptyMap<String, Int>()))
              }
              .onErrorResume { ServerResponse.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(emptyMap<String, Int>())) }
}

