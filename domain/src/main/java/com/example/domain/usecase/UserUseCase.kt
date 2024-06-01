package com.example.domain.usecase

import com.example.domain.repostories.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke() =repository.fetchUser()
}