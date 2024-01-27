package hu.varsanyi.punkapiapp.domain.models

data class Beer(
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val tagline: String,
    val description: String
)
