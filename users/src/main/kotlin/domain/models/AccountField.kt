package domain.models

sealed class AccountField<out T> {
    object Undefined : AccountField<Nothing>()
    data class Defined<T>(val value: T) : AccountField<T>()

    fun getOrNull(): T? {
        return when (this) {
            is Defined -> this.value
            Undefined -> null
        }
    }
}
