package com.upreality.expensesspringbackend.users.domain

import com.upreality.expensesspringbackend.users.domain.models.User

interface IUsersRepository {
    fun create(user: User): User
    fun getById(id: Long): User?
}