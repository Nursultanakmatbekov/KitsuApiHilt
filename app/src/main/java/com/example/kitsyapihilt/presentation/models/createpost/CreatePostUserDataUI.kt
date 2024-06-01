package com.example.kitsyapihilt.presentation.models.createpost

import com.example.domain.models.createpost.CreatePostUserData


data class CreatePostUserDataUI(
    val type: String = "users",
    val id: String
)

fun CreatePostUserData.toUI(): CreatePostUserDataUI = CreatePostUserDataUI(
    type, id
)
