package com.example.shared_kmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.shared_kmp.navigation.NavigationManager
import com.example.shared_kmp.navigation.Screen
import com.example.shared_kmp.presentation.theme.AppTheme
import com.example.shared_kmp.presentation.ui.HomeScreen
import com.example.shared_kmp.presentation.ui.LoginScreen
import com.example.shared_kmp.presentation.ui.SplashScreen
import com.example.shared_kmp.presentation.viewmodel.HomeViewModel
import com.example.shared_kmp.presentation.viewmodel.LoginViewModel
import org.koin.compose.koinInject

@Composable
fun App() {
    AppTheme {
        val loginViewModel: LoginViewModel = koinInject()
        val homeViewModel: HomeViewModel = koinInject()
        val navigationManager = remember { NavigationManager() }

        // Track session check
        LaunchedEffect(Unit) {
            val savedUser = loginViewModel.getSavedUserDirectly()
            if (savedUser != null) {
                // User logged in
                navigationManager.navigateTo(Screen.Home)
                homeViewModel.refreshUser()
            } else {
                // Not logged in
                navigationManager.navigateTo(Screen.Login)
            }
        }

        Surface(modifier = Modifier.fillMaxSize()) {
            val currentScreen by navigationManager.currentScreen.collectAsState()

            when (currentScreen) {
                is Screen.Splash -> SplashScreen()
                is Screen.Login -> LoginScreen(
                    viewModel = loginViewModel,
                    onLoginSuccess = {
                        navigationManager.navigateTo(Screen.Home)
                    }
                )
                is Screen.Home -> HomeScreen(
                    viewModel = homeViewModel,
                    onLogout = {
                        loginViewModel.logout()
                        navigationManager.navigateTo(Screen.Login)
                    }
                )
            }
        }
    }
}
