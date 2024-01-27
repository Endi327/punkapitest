package hu.varsanyi.punkapiapp.data.usecases

import hu.varsanyi.punkapiapp.data.store.BeerRepository
import hu.varsanyi.punkapiapp.domain.models.Beer
import hu.varsanyi.punkapiapp.domain.usecases.GetBeerByIdUseCase
import hu.varsanyi.punkapiapp.domain.usecases.core.UseCase
import kotlinx.coroutines.CoroutineDispatcher

class DefaultGetBeerDetailsByIdUseCase(
    private val beerRepository: BeerRepository,
    coroutineDispatcher: CoroutineDispatcher
) : UseCase<Int?, Beer>(coroutineDispatcher), GetBeerByIdUseCase{

    override suspend fun execute(parameters: Int?): Beer {
        return beerRepository.getBeerById(parameters!!)!!
    }
}