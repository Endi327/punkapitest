package hu.varsanyi.punkapiapp.data.store

import hu.varsanyi.punkapiapp.domain.models.Beer

interface BeerRepository {
    fun saveLikedBeer(beer: Beer)

    fun getLikedBeers(): List<Beer>

    fun getBeerById(beerId: Int): Beer?
}