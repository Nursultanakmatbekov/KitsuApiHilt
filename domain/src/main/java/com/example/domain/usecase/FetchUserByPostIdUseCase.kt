package com.example.domain.usecase

import com.example.domain.repostories.UserRepository
import javax.inject.Inject

class FetchUserByPostIdUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: String) = repository.fetchUserByPostId(id)
}