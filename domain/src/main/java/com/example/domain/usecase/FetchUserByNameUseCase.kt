package com.example.domain.usecase

import com.example.domain.repostories.UserRepository
import javax.inject.Inject

class FetchUserByNameUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(name: String) = repository.fetchUsersByName(name)
}