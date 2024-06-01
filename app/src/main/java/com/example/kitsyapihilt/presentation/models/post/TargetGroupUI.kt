package com.example.kitsyapihilt.presentation.models.post

import com.example.domain.models.post.TargetGroup


data class TargetGroupUI(
    val links: LinksUI
)

fun TargetGroup.toUI(): TargetGroupUI = TargetGroupUI(
    links.toUI()
)