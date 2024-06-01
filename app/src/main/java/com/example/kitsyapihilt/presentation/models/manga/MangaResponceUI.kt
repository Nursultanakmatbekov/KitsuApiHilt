package com.example.kitsyapihilt.presentation.models.manga


data class MangaResponceUI<T>(
    val data: List<T>,
    val links: LinksUI
)