package hu.varsanyi.punkapiapp.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class AppApiClient {
    val client: HttpClient = HttpClient(Android) {

        defaultRequest {
            host = BASE_URL
            url {
                protocol = URLProtocol.HTTPS
            }
        }

        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }


    private companion object {
        const val BASE_URL = "api.punkapi.com/v2"
    }
}