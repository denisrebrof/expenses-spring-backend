package com.upreality.users.domain

import com.upreality.users.domain.models.User

class UserUseCases(
        private val repository: IUsersRepository
) {
    fun getUser(userId: Long): User {
        return repository.getById(userId)
    }

    fun createUser(): User {

    }
}