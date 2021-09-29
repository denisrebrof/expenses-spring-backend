package com.upreality.expensesspringbackend.users.data

import com.upreality.expensesspringbackend.users.domain.models.*
import com.upreality.expensesspringbackend.users.domain.models.Credentials.Base.Email
import com.upreality.expensesspringbackend.users.domain.models.Credentials.ProviderBased.Google

object UserModelConverter {

    fun toUserModel(user: User): UserModel {
        val emailData = user.credentials.filterIsInstance(Email::class.java).firstOrNull()?.email
        val googleIdData = user.credentials.filterIsInstance(Google::class.java).firstOrNull()?.googleId
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
        return User(
            id = model.id,
            credentials = credentials,
            createdDate = model.createdDate,
            token = model.token?.let(TokenState::Defined) ?: TokenState.Undefined,
            refreshToken = model.refreshToken?.let(TokenState::Defined) ?: TokenState.Undefined,
            nickname = model.nickname?.let { nick -> AccountField.Defined(nick) } ?: AccountField.Undefined,
            password = model.passwordHash?.let { hash -> PasswordState.Defined(hash) } ?: PasswordState.Undefined
        )
    }
}