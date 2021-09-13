package com.upreality.users.domain.models

sealed class Credentials {
    sealed class Base() : Credentials() {
        data class Email(
                val email: String,
                val verified: Boolean = false
        ) : Base()

        data class Phone(
                val phone: String,
                val verified: Boolean = false
        ) : Base()
    }

    sealed class ProviderBased : Credentials() {
        data class Google(val googleId: Long) : ProviderBased()
    }
}