package com.example.kitsyapihilt.presentation.models.anime


data class AnimeResponceUI<T>(
    val data: List<T>,
    val links: LinksUI
)