package com.upreality.users.domain

import com.upreality.users.domain.models.User

interface IUsersRepository {
    fun create(user: User): User
    fun getById(id: Long): User
}