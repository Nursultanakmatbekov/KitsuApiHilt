package com.example.kitsyapihilt.presentation.ui.fragments.user

import androidx.paging.PagingData
import com.example.domain.models.user.User
import com.example.domain.usecase.UserUseCase
import com.example.kitsyapihilt.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : BaseViewModel() {

    val usersFlow: Flow<PagingData<User>> = userUseCase.invoke()

    init {
        usersFlow.flatMapLatest {
            userUseCase.invoke()
        }
    }
}