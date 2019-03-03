package com.example.study

import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository

object WebSessionServerSecurityContextRepositoryFactory {
    val instance = WebSessionServerSecurityContextRepository()
}