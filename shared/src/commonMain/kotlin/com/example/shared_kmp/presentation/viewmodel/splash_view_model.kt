package com.example.shared_kmp.presentation.viewmodel

import com.example.shared_kmp.domain.usecase.UserUseCase
import com.example.shared_kmp.navigation.NavigationManager
import com.example.shared_kmp.navigation.Screens
import kotlinx.coroutines.launch

// Updated SplashViewModel
class SplashViewModel(
    private val navigationManager: NavigationManager,
    private val userUseCase: UserUseCase
) : ViewModel() { // Use androidx.lifecycle.ViewModel

    init {
        checkAuth()
    }

    private fun checkAuth() {
        // viewModelScope handles cancellation automatically when VM is cleared
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