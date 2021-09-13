package com.upreality.users.domain.models

sealed class PasswordState {
    object Undefined : PasswordState()
    data class Defined(val hash: String) : PasswordState()

    fun getHashOrNull(): String? {
        return when (this) {
            is PasswordState.Defined -> this.hash
            PasswordState.Undefined -> null
        }
    }
}
