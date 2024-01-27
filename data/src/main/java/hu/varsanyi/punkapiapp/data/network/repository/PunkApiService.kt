package hu.varsanyi.punkapiapp.data.network.repository

import hu.varsanyi.punkapiapp.data.network.response.BeerDto

fun interface PunkApiService {
    suspend fun getRandomBeer(): BeerDto
}