package hu.varsanyi.punkapiapp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeerDto(
    val id: String,
    val name: String,
    val tagline: String,
    @SerialName("image_url") val imageUrl: String,
    val description: String
)
