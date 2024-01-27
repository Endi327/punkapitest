package hu.varsanyi.punkapiapp.domain.usecases.core

import hu.varsanyi.punkapiapp.domain.errors.AppError
import hu.varsanyi.punkapiapp.domain.errors.toAppError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

//Note: from previous projects
abstract class UseCase<in Params, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: Params): UseCaseResult<AppError, R> {
        return runCatching {
            withContext(coroutineDispatcher) {
                execute(parameters)
            }
        }.fold(
            onSuccess = {
                UseCaseResult.Success(it)
            },
            onFailure = {
                UseCaseResult.Failure(it.toAppError())
            }
        )
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: Params): R
}

fun interface IUseCase<in Params, R> {
    suspend operator fun invoke(parameters: Params): UseCaseResult<AppError, R>
}