package hu.varsanyi.punkapiapp.data.store

import hu.varsanyi.punkapiapp.domain.models.Beer

class BeerRepositoryImplementation : BeerRepository {
    private val likedBeers: MutableList<Beer> = mutableListOf()
    override fun saveLikedBeer(beer: Beer) {
        likedBeers.add(beer)
    }

    override fun getLikedBeers(): List<Beer> {
        return likedBeers
    }

    override fun getBeerById(beerId: Int): Beer? {
        return likedBeers.firstOrNull { it.id == beerId }
    }
}