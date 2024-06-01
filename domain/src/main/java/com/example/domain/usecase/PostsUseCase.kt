package com.example.domain.usecase

import com.example.domain.repostories.PostRepository
import javax.inject.Inject

class PostsUseCase @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke() = repository.fetchPosts()
}