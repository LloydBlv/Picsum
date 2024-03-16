package com.example.data

import com.slack.eithernet.ApiResult


fun <T : Any, R : Any, E : Any> ApiResult<T, E>.mapValue(map: (T) -> R): Result<R> =
    when (this) {
        is ApiResult.Success -> Result.success(map(value))
        is ApiResult.Failure.NetworkFailure -> Result.failure(this.error)
        is ApiResult.Failure.UnknownFailure -> Result.failure(this.error)
        else -> Result.failure(UnknownFailure())
    }

class UnknownFailure : Throwable()