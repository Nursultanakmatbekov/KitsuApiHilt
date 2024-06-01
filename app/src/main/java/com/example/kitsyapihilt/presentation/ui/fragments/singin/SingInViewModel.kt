package com.example.kitsyapihilt.presentation.ui.fragments.singin

import com.example.domain.usecase.SingInUseCase
import com.example.kitsyapihilt.presentation.base.BaseViewModel
import com.example.kitsyapihilt.presentation.models.auth.LoginResponseUI
import com.example.kitsyapihilt.presentation.models.auth.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
    private val singInUseCase: SingInUseCase
) : BaseViewModel() {

    private val _getSingInState = mutableUIStateFlow<LoginResponseUI>()
    val getSingInState = _getSingInState.asStateFlow()

    fun login(email: String, password: String) {
        singInUseCase(
            email = email,
            password = password
        ).gatRequest(_getSingInState) { it.toUI() }
    }
}