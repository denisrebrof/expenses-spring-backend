package domain

import domain.models.User

class UserUseCases(
    private val repository: IUsersRepository
) {
    fun getUser(userId: Long): User {
        return repository.getById(userId)
    }

    fun createUser(): User {
        return repository.create(User(0L))
    }
}