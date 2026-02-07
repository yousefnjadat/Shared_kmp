package com.example.shared_kmp.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationManager {
    private val _currentScreen = MutableStateFlow<Screens>(Screens.Splash)
    val currentScreen: StateFlow<Screens> = _currentScreen

    fun navigateTo(screen: Screens) {
        _currentScreen.value = screen
    }
}