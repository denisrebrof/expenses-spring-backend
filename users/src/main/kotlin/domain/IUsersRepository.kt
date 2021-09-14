package domain

import domain.models.User

interface IUsersRepository {
    fun create(user: User): User
    fun getById(id: Long): User
}