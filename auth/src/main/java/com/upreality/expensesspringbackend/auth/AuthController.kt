package com.upreality.expensesspringbackend.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userDetailsService: UserDetailsServiceImpl

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    @RequestMapping("/hello")
    fun hello(): String {
        return "Nil here"
    }

    @RequestMapping("/hello2")
    fun helloTwo(): String {
        return "N0l here"
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthenticationRequest): ResponseEntity<*> = with(request) {
        UsernamePasswordAuthenticationToken(username, password).let(authenticationManager::authenticate)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
        val jwt = jwtUtil.generateToken(userDetails)
        return@with when {
            jwt.isNullOrEmpty() -> ResponseEntity.internalServerError().build()
            else -> ResponseEntity.ok<Any>(AuthenticationResponse(jwt))
        }
    }
}