package com.example.kitsyapihilt.presentation.models.anime

import com.example.domain.models.anime.PosterImage


data class PosterImageUI(
    val small: String = "",
    val original: String = "",
    val large: String = "",
    val tiny: String = "",
    val medium: String = ""
)

fun PosterImage.toUI(): PosterImageUI = PosterImageUI(
    small, original, large, tiny, medium
)