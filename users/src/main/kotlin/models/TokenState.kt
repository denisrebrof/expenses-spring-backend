package com.upreality.users.domain.models

sealed class TokenState {

    object Undefined : TokenState()

    data class Defined(
            val token: String
    ) : TokenState()

    fun getOrNull(): String? {
        return when (this) {
            is TokenState.Defined -> this.token
            TokenState.Undefined -> null
        }
    }

}
