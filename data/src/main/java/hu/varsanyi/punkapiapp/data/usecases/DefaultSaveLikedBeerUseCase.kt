package hu.varsanyi.punkapiapp.data.usecases

import hu.varsanyi.punkapiapp.data.store.BeerRepository
import hu.varsanyi.punkapiapp.domain.models.Beer
import hu.varsanyi.punkapiapp.domain.usecases.SaveLikedBeerUseCase
import hu.varsanyi.punkapiapp.domain.usecases.core.UseCase
import kotlinx.coroutines.CoroutineDispatcher

class DefaultSaveLikedBeerUseCase(
    private val beerRepository: BeerRepository,
    coroutineDispatcher: CoroutineDispatcher
): UseCase<Beer?, Unit>(coroutineDispatcher), SaveLikedBeerUseCase {

    override suspend fun execute(parameters: Beer?) {
        beerRepository.saveLikedBeer(parameters!!)
    }
}