package hu.varsanyi.punkapiapp.data.di

import hu.varsanyi.punkapiapp.data.network.AppApiClient
import hu.varsanyi.punkapiapp.data.network.repository.PunkApiService
import hu.varsanyi.punkapiapp.data.network.repository.PunkApiServiceImplementation
import hu.varsanyi.punkapiapp.data.store.BeerRepository
import hu.varsanyi.punkapiapp.data.store.BeerRepositoryImplementation
import hu.varsanyi.punkapiapp.data.usecases.DefaultGetBeerDetailsByIdUseCase
import hu.varsanyi.punkapiapp.data.usecases.DefaultGetLikedBeerListUseCase
import hu.varsanyi.punkapiapp.data.usecases.DefaultGetRandomBeerUseCase
import hu.varsanyi.punkapiapp.data.usecases.DefaultSaveLikedBeerUseCase
import hu.varsanyi.punkapiapp.domain.usecases.GetBeerByIdUseCase
import hu.varsanyi.punkapiapp.domain.usecases.GetLikedBeerListUseCase
import hu.varsanyi.punkapiapp.domain.usecases.GetRandomBeerUseCase
import hu.varsanyi.punkapiapp.domain.usecases.SaveLikedBeerUseCase
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> { AppApiClient().client }

    single { Dispatchers.Default }

    single<PunkApiService> { PunkApiServiceImplementation(get()) }

    single<BeerRepository> { BeerRepositoryImplementation() }

    factory<GetRandomBeerUseCase> { DefaultGetRandomBeerUseCase(get(), get()) }

    factory<GetLikedBeerListUseCase> { DefaultGetLikedBeerListUseCase(get(), get()) }

    factory<SaveLikedBeerUseCase> { DefaultSaveLikedBeerUseCase(get(), get()) }

    factory<GetBeerByIdUseCase> { DefaultGetBeerDetailsByIdUseCase(get(), get()) }
}