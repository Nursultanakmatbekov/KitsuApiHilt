package com.example.kitsyapihilt.presentation.ui.fragments.createpost

import com.example.domain.usecase.CreatePostUseCase
import com.example.domain.usecase.FetchUserByNameUseCase
import com.example.kitsyapihilt.presentation.base.BaseViewModel
import com.example.kitsyapihilt.presentation.models.user.UserUI
import com.example.kitsyapihilt.presentation.models.user.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val fetchUserByNameUseCase: FetchUserByNameUseCase,
    private val createPostUseCase: CreatePostUseCase
) : BaseViewModel() {

    private val _fetchCreatePostState = mutableUIStateFlow<Unit>()
    val fetchCreatePostState = _fetchCreatePostState.asStateFlow()

    private val _userFlow = mutableUIStateFlow<List<UserUI>>()
    val userFlow = _userFlow.asStateFlow()

    fun getUser(username: String) {
        fetchUserByNameUseCase(username).gatRequest(_userFlow) { data -> data.map { it.toUI() } }
    }

    fun createPost(userId: String, content: String, nsfw: Boolean, spoiler: Boolean) {
        createPostUseCase(
            userId = userId, content = content,
            nsfw = nsfw, spoiler = spoiler
        ).gatherRequest(_fetchCreatePostState)
    }
}

