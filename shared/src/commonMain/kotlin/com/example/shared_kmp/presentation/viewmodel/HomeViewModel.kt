package com.example.shared_kmp.presentation.viewmodel

import com.example.shared_kmp.data.datasource.local.UserLocalDataSource
import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.navigation.NavigationManager
import com.example.shared_kmp.navigation.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val navigationManager: NavigationManager,
    private val userLocalDataSource: UserLocalDataSource,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _userState = MutableStateFlow<LoginResponse?>(null)
    val userState: StateFlow<LoginResponse?> = _userState

    init {
        loadUser()
    }

    private fun loadUser() {
        coroutineScope.launch {
            _userState.value = userLocalDataSource.getUser()
        }
    }

    fun refreshUser() {
        coroutineScope.launch {
            loadUser()
        }
    }

    fun logout() {
        userLocalDataSource.deleteUser()
        navigationManager.navigateTo(Screens.Login)
    }

}