package hu.varsanyi.punkapiapp.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

class AppApiClient {
    val client: HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }
}