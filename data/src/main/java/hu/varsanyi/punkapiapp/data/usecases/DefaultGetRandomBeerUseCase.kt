package hu.varsanyi.punkapiapp.data.usecases

import hu.varsanyi.punkapiapp.data.mappers.toBeer
import hu.varsanyi.punkapiapp.data.network.repository.PunkApiService
import hu.varsanyi.punkapiapp.domain.models.Beer
import hu.varsanyi.punkapiapp.domain.usecases.GetRandomBeerUseCase
import hu.varsanyi.punkapiapp.domain.usecases.core.UseCase
import kotlinx.coroutines.CoroutineDispatcher

class DefaultGetRandomBeerUseCase(
    private val punkApiService: PunkApiService,
    coroutineDispatcher: CoroutineDispatcher
) : UseCase<Unit, Beer>(coroutineDispatcher), GetRandomBeerUseCase {
    override suspend fun execute(parameters: Unit): Beer {
        val beerDto = punkApiService.getRandomBeer().first()

        return beerDto.toBeer()
    }
}