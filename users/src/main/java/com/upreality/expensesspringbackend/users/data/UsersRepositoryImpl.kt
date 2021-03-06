package com.upreality.expensesspringbackend.users.data

import com.upreality.expensesspringbackend.users.domain.IUsersRepository
import com.upreality.expensesspringbackend.users.domain.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UsersRepositoryImpl @Autowired constructor(
    private val dao: IUsersDao
) : IUsersRepository {

    override fun create(user: User): User {
        return user
            .copy(createdDate = LocalDateTime.now())
            .let(UserModelConverter::toUserModel)
            .let(dao::insert)
            .let(UserModelConverter::fromUserModel)
    }

    override fun getById(id: Long): User? {
        return dao.getById(id)?.let(UserModelConverter::fromUserModel)
    }
}