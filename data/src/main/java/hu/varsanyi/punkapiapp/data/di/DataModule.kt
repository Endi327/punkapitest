package hu.varsanyi.punkapiapp.data.di

import hu.varsanyi.punkapiapp.data.network.AppApiClient
import hu.varsanyi.punkapiapp.data.network.repository.PunkApiService
import hu.varsanyi.punkapiapp.data.network.repository.PunkApiServiceImplementation
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> { AppApiClient().client }

    single<PunkApiService> { PunkApiServiceImplementation(get()) }
}