package com.example.domain.models

sealed interface Result<out T> {
    fun getResultOrNull(): T? = when (this) {
        is Success -> value
        else -> null
    }
    data class Success<out T>(val value: T) : Result<T>
    data class Failure(val error: Throwable?) : Result<Nothing>
}

inline fun <reified T> T.toSuccess(): Result<T> = Result.Success(this)
