package com.example.shared_kmp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
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
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val navigationManager: NavigationManager = koinInject()
            val screen by navigationManager.currentScreen.collectAsState()

            Crossfade(targetState = screen) { currentScreen ->
                when (currentScreen) {
                    Screens.Splash -> {
                        val vm: SplashViewModel = koinViewModel()
                        SplashScreen(vm)
                    }

                    Screens.Login -> {
                        val vm: LoginViewModel = koinViewModel()
                        LoginScreen(viewModel = vm)
                    }

                    Screens.Home -> {
                        val vm: HomeViewModel = koinViewModel()
                        HomeScreen(viewModel = vm)
                    }
                }
            }
        }
    }
}