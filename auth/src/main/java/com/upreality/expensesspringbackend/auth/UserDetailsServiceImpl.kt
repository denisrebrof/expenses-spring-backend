package com.upreality.expensesspringbackend.auth

import com.upreality.expensesspringbackend.users.domain.UserUseCases
import com.upreality.expensesspringbackend.users.domain.models.PasswordState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl: UserDetailsService {

    @Autowired
    private lateinit var userUseCases: UserUseCases

    override fun loadUserByUsername(username: String?): UserDetails {
        val userId = username?.toLongOrNull() ?: throw ClassCastException("username must be long!")
        val user = userUseCases.getUser(userId) ?: throw UsernameNotFoundException("user with id=$userId not found!")
        val password = when(val passwordState = user.password){
            is PasswordState.Defined -> passwordState.hash
            PasswordState.Undefined -> String()
        }
        return User(user.id.toString(), password, arrayListOf())
    }
}