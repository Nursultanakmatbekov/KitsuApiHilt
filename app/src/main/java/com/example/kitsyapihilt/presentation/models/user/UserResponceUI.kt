package com.example.kitsyapihilt.presentation.models.user

import com.example.domain.models.user.UserResponse
import com.example.kitsyapihilt.presentation.models.anime.LinksUI


data class UserResponceUI<T>(
    val data: List<T>,
    val links: LinksUI
)

data class UserResponseUI(
    val data: UserUI
)

fun UserResponse.toUI() = UserResponseUI(
    data.toUI()
)