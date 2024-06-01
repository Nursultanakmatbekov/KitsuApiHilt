package com.example.kitsyapihilt.presentation.models.anime

import com.example.domain.models.anime.Anime


data class AnimeUI(
    val attributes: AttributesUI,
    val id: String = "",
    val type: String = ""
)

fun Anime.toUI() : AnimeUI = AnimeUI(
    attributes.toUI(),
    id,
    type
)