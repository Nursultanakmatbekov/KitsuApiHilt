package com.example.kitsyapihilt.presentation.models.createpost

import com.example.domain.models.createpost.CreatePostUser


data class CreatePostUserUI(
    val data: CreatePostUserDataUI
)

fun CreatePostUser.toUI(): CreatePostUserUI = CreatePostUserUI(
    data.toUI()
)