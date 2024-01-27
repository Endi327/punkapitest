package hu.varsanyi.punkapiapp.data.network.repository

import hu.varsanyi.punkapiapp.data.network.response.BeerDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.encodedPath

class PunkApiServiceImplementation(
    private val client: HttpClient
): PunkApiService {
    override suspend fun getRandomBeer(): BeerDto {
        return client.get {
            url {
                encodedPath = "/beers/random"
            }
        }.body()
    }


    private companion object {
        const val BASE_URL = "https://api.punkapi.com/v2"
    }
}