package com.example.kitsyapihilt.presentation.models.anime

import com.example.domain.models.anime.Titles


data class TitlesUI(
    val jaJp: String = "",
    val enJp: String = ""
)

fun Titles.toUI(): TitlesUI = TitlesUI(
    jaJp, enJp
)