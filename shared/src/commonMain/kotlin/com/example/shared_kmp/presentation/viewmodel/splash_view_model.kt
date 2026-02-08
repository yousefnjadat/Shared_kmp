package com.example.shared_kmp.presentation.viewmodel

import com.example.shared_kmp.data.datasource.local.UserLocalDataSource
import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.navigation.NavigationManager
import com.example.shared_kmp.navigation.Screens

class SplashViewModel(
    private val navigationManager: NavigationManager,
    private val userLocalDataSource: UserLocalDataSource
) {

    init {
        checkAuth()
    }

    fun checkAuth() {
        val user: LoginResponse? = userLocalDataSource.getUser()

        if (user != null && user.accessToken.isNotEmpty()) {
            navigationManager.navigateTo(Screens.Home)
        } else {
            navigationManager.navigateTo(Screens.Login)
        }
    }
}