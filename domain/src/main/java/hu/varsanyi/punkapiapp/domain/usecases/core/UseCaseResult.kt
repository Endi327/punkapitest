package hu.varsanyi.punkapiapp.domain.usecases.core

import hu.varsanyi.punkapiapp.domain.errors.AppError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

//Note: from previous projects
sealed class UseCaseResult<out L : AppError, out R> {
    data class Failure<out L : AppError>(val error: L) : UseCaseResult<L, Nothing>()

    data class Success<out R>(val data: R) : UseCaseResult<Nothing, R>()

    inline fun <Result> foldResult(
        onFailure: (L) -> Result,
        onSuccess: (R) -> Result
    ): Result {
        return when (this) {
            is Failure -> onFailure(error)
            is Success -> onSuccess(data)
        }
    }
}

@ExperimentalCoroutinesApi
inline fun <Result, L : AppError, R> Flow<UseCaseResult<L, R>>.foldResult(
    crossinline onFailure: (L) -> Result,
    crossinline onSuccess: (R) -> Result
): Flow<Result> =
    this.mapLatest {
        it.foldResult(onFailure, onSuccess)
    }