package com.example.kitsyapihilt.presentation.models.post

import com.example.domain.models.post.User


data class UserUI(
    val links: LinksUI
)

fun User.toUI(): UserUI = UserUI(
    links.toUI()
)