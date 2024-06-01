package com.example.kitsyapihilt.presentation.ui.fragments.post

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.models.post.DataItem
import com.example.domain.models.user.User
import com.example.domain.usecase.FetchUserByPostIdUseCase
import com.example.domain.usecase.PostsUseCase
import com.example.kitsyapihilt.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postsUseCase: PostsUseCase,
    private val fetchUserByPostIdUseCase: FetchUserByPostIdUseCase
) : BaseViewModel() {

    suspend fun fetchUser(id: String): User {
        return fetchUserByPostIdUseCase(id)
    }

    val postsFlow: Flow<PagingData<DataItem>> = postsUseCase().cachedIn(viewModelScope)
}
