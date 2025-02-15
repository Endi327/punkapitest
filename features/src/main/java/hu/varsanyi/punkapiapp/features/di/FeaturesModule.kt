package hu.varsanyi.punkapiapp.features.di

import hu.varsanyi.punkapiapp.features.beerdetails.BeerDetailsViewModel
import hu.varsanyi.punkapiapp.features.beerlist.BeerListViewModel
import hu.varsanyi.punkapiapp.features.beermatcher.BeerMatcherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featuresModule = module {
    viewModel { BeerMatcherViewModel(get(), get()) }

    viewModel { BeerListViewModel(get()) }

    viewModel { BeerDetailsViewModel(get()) }
}