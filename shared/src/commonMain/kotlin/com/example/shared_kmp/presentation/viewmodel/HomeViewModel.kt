package com.example.shared_kmp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.shared_kmp.domain.usecase.UserUseCase
import com.example.shared_kmp.navigation.NavigationManager
import com.example.shared_kmp.navigation.Screens
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val userUseCase: UserUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {

    val userState = flow {
        emit(userUseCase.getUser())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun logout() {
        viewModelScope.launch {
            userUseCase.deleteUser()
            navigationManager.navigateTo(Screens.Login)
        }
    }
}