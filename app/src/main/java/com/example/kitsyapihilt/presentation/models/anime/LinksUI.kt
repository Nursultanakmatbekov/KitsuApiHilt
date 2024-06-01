package com.example.kitsyapihilt.presentation.models.anime

import com.example.domain.models.anime.Links


data class LinksUI(
    val next: String?,
    val last: String = "",
    val first: String = ""
)

fun Links.toUI(): LinksUI = LinksUI(
    next, last, first
)

