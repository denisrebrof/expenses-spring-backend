package com.upreality.expensesspringbackend.users.domain.models

sealed class PasswordState {
    object Undefined : PasswordState()
    data class Defined(val hash: String) : PasswordState()

    fun getHashOrNull(): String? {
        return when (this) {
            is Defined -> this.hash
            Undefined -> null
        }
    }
}
