package hu.varsanyi.punkapiapp.data.mappers

import hu.varsanyi.punkapiapp.data.network.response.BeerDto
import hu.varsanyi.punkapiapp.domain.models.Beer

fun BeerDto.toBeer(): Beer = Beer(
    id = id,
    name = name,
    image = imageUrl,
    tagline = tagline,
    description = description
)