package data

import domain.IUsersRepository
import domain.models.User
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

class UsersRepositoryImpl(
    @Autowired
    private val dao: IUsersDao
) : IUsersRepository {

    override fun create(user: User): User {
        return user
            .copy(createdDate = LocalDateTime.now())
            .let(UserModelConverter::toUserModel)
            .let(dao::insert)
            .let(UserModelConverter::fromUserModel)
    }

    override fun getById(id: Long): User {
        return dao.getById(id).let(UserModelConverter::fromUserModel)
    }
}