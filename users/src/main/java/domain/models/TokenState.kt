package domain.models

sealed class TokenState {

    object Undefined : TokenState()

    data class Defined(
        val token: String
    ) : TokenState()

    fun getOrNull(): String? {
        return when (this) {
            is Defined -> this.token
            Undefined -> null
        }
    }

}
