package com.example.data

import com.example.domain.models.Result
import com.slack.eithernet.ApiResult


fun <T : Any, R : Any, E: Any> ApiResult<T, E>.mapValue(map: (T) -> R): Result<R> =
    when (this) {
        is ApiResult.Success -> Result.Success(map(value))
        is ApiResult.Failure.NetworkFailure -> Result.Failure(this.error)
        is ApiResult.Failure.UnknownFailure -> Result.Failure(this.error)
        else -> Result.Failure(null)
    }