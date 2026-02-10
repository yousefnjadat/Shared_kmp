package com.example.shared_kmp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared_kmp.domain.usecase.UserUseCase
import com.example.shared_kmp.navigation.NavigationManager
import com.example.shared_kmp.navigation.Screens
import kotlinx.coroutines.launch

class SplashViewModel(
    private val navigationManager: NavigationManager,
    private val userUseCase: UserUseCase
) : ViewModel() {

    init {
        checkAuth()
    }

    private fun checkAuth() {
        viewModelScope.launch {
            val user = userUseCase.getUser()
            if (user?.accessToken?.isNotEmpty() == true) {
                navigationManager.navigateTo(Screens.Home)
            } else {
                navigationManager.navigateTo(Screens.Login)
            }
        }
    }
}