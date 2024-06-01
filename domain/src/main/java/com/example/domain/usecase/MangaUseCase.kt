package com.example.domain.usecase

import com.example.domain.repostories.MangaRepository
import javax.inject.Inject

class MangaUseCase @Inject constructor(
    private val repository: MangaRepository
) {

    operator fun invoke(text: String?, categories: List<String>? ) = repository.fetchManga(text,categories)
}