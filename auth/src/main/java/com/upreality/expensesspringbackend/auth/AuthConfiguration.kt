package com.upreality.expensesspringbackend.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthConfiguration {
    @Bean
    fun getRequestFilter(): JWTRequestFilter = JWTRequestFilter()
}