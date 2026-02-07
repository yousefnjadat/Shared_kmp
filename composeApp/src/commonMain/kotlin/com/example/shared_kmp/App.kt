package com.example.shared_kmp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.shared_kmp.navigation.NavigationManager
import com.example.shared_kmp.navigation.Screens
import com.example.shared_kmp.presentation.theme.AppTheme
import com.example.shared_kmp.presentation.ui.HomeScreen
import com.example.shared_kmp.presentation.ui.LoginScreen
import com.example.shared_kmp.presentation.ui.SplashScreen
import com.example.shared_kmp.presentation.viewmodel.HomeViewModel
import com.example.shared_kmp.presentation.viewmodel.LoginViewModel
import com.example.shared_kmp.presentation.viewmodel.SplashViewModel
import org.koin.compose.koinInject

@Composable
fun App() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navigationManager: NavigationManager = koinInject()
            val screen by navigationManager.currentScreen.collectAsState()

            Crossfade(
                targetState = screen,
                label = "ScreenTransition"
            ) { currentScreen ->
                when (currentScreen) {
                    Screens.Splash -> {
                        val splashViewModel: SplashViewModel = koinInject()
                        SplashScreen(splashViewModel)
                    }

                    Screens.Login -> {
                        val loginViewModel: LoginViewModel = koinInject()
                        val homeViewModel: HomeViewModel = koinInject()

                        LoginScreen(
                            viewModel = loginViewModel,
                            onLoginSuccess = {
                                homeViewModel.refreshUser()
                                navigationManager.navigateTo(Screens.Home)
                            }
                        )
                    }

                    Screens.Home -> {
                        val homeViewModel: HomeViewModel = koinInject()
                        val loginViewModel: LoginViewModel = koinInject()

                        HomeScreen(
                            viewModel = homeViewModel,
                            onLogout = {
                                loginViewModel.logout()
                                navigationManager.navigateTo(Screens.Login)
                            }
                        )
                    }
                }
            }
        }
    }
}