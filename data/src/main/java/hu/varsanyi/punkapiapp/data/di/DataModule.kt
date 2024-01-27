package hu.varsanyi.punkapiapp.data.di

import hu.varsanyi.punkapiapp.data.network.AppApiClient
import hu.varsanyi.punkapiapp.data.network.repository.PunkApiService
import hu.varsanyi.punkapiapp.data.network.repository.PunkApiServiceImplementation
import hu.varsanyi.punkapiapp.data.usecases.DefaultGetRandomBeerUseCase
import hu.varsanyi.punkapiapp.domain.usecases.GetRandomBeerUseCase
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> { AppApiClient().client }

    single { Dispatchers.Default }

    single<PunkApiService> { PunkApiServiceImplementation(get()) }

    factory<GetRandomBeerUseCase> { DefaultGetRandomBeerUseCase(get(), get())  }
}