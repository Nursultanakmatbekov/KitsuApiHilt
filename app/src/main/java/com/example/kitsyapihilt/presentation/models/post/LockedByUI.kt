package com.example.kitsyapihilt.presentation.models.post

import com.example.domain.models.post.LockedBy


data class LockedByUI(
    val links: LinksUI
)

fun LockedBy.toUI(): LockedByUI = LockedByUI(
    links.toUI()
)