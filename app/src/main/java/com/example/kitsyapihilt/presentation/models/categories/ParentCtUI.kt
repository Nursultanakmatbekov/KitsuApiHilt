package com.example.kitsyapihilt.presentation.models.categories

import com.example.domain.models.categories.ParentCt


data class ParentCtUI(
    val links: LinksCtUI
)

fun ParentCt.toUI(): ParentCtUI = ParentCtUI(
    links.toUI()
)