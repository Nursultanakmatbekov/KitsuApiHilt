package com.example.kitsyapihilt.presentation.models.post

import com.example.domain.models.post.Meta

data class MetaUI(
    val count: Int = 0
)

fun Meta.toUI(): MetaUI = MetaUI(
    count
)