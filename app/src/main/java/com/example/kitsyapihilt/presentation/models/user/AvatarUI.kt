package com.example.kitsyapihilt.presentation.models.user

import com.example.domain.models.user.Avatar


data class AvatarUI(
    val original: String? = null
)

fun Avatar.toUI(): AvatarUI = AvatarUI(
    original
)