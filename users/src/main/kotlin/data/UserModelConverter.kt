package com.upreality.users.data

import com.upreality.users.domain.models.*

object UserModelConverter {

    fun toUserModel(user: User): UserModel {
        val emailData = user.credentials
                .filterIsInstance(Credentials.Base.Email::class.java)
                .firstOrNull()
                ?.email
        val googleIdData = user.credentials
                .filterIsInstance(Credentials.ProviderBased.Google::class.java)
                .firstOrNull()
                ?.googleId
        return UserModel(
                id = user.id,
                email = emailData,
                googleId = googleIdData,
                nickname = user.nickname.getOrNull(),
                passwordHash = user.password.getHashOrNull(),
                token = user.token.getOrNull(),
                refreshToken = user.refreshToken.getOrNull(),
                createdDate = user.createdDate
        )
    }

    fun fromUserModel(model: UserModel): User {
        val credentials = mutableListOf<Credentials>()
        model.email?.let(Credentials.Base::Email)?.let(credentials::add)
        model.googleId?.let(Credentials.ProviderBased::Google)?.let(credentials::add)
        val tokenState = model.token?.let(TokenState::Defined) ?: TokenState.Undefined
        val refreshTokenState = model.refreshToken?.let(TokenState::Defined) ?: TokenState.Undefined
        val nicknameState = model.nickname?.let { nick ->
            AccountField.Defined(nick)
        } ?: AccountField.Undefined
        val passwordState = model.passwordHash?.let { password ->
            PasswordState.Defined(password)
        } ?: PasswordState.Undefined
        return User(
                id = model.id,
                credentials = credentials,
                createdDate = model.createdDate,
                token = tokenState,
                refreshToken = refreshTokenState,
                nickname = nicknameState,
                password = passwordState
        )
    }
}