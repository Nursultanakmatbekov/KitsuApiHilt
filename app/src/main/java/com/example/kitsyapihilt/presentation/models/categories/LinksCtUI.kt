package com.example.kitsyapihilt.presentation.models.categories

import com.example.domain.models.categories.LinksCt


data class LinksCtUI(
    val next: String = "",
    val last: String = "",
    val first: String = ""
)

fun LinksCt.toUI(): LinksCtUI = LinksCtUI(
    next, last, first
)