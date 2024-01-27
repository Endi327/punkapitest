package hu.varsanyi.punkapiapp.data.usecases

import hu.varsanyi.punkapiapp.data.store.BeerRepository
import hu.varsanyi.punkapiapp.domain.models.Beer
import hu.varsanyi.punkapiapp.domain.usecases.GetLikedBeerListUseCase
import hu.varsanyi.punkapiapp.domain.usecases.core.UseCase
import kotlinx.coroutines.CoroutineDispatcher

class DefaultGetLikedBeerListUseCase(
    private val beerRepository: BeerRepository,
    coroutineDispatcher: CoroutineDispatcher
) : UseCase<Unit, List<Beer>>(coroutineDispatcher), GetLikedBeerListUseCase{

    override suspend fun execute(parameters: Unit): List<Beer> {
        return beerRepository.getLikedBeers()
    }
}