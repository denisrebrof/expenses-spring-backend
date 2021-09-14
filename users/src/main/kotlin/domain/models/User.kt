package domain.models

import domain.models.AccountField
import domain.models.Credentials
import domain.models.PasswordState
import domain.models.TokenState
import java.time.LocalDateTime

data class User(
    val id: Long,
    val credentials: List<Credentials> = listOf(),
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val token: TokenState = TokenState.Undefined,
    val refreshToken: TokenState = TokenState.Undefined,
    val nickname: AccountField<String> = AccountField.Undefined,
    val password: PasswordState = PasswordState.Undefined,
)