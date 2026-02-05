package com.example.shared_kmp.navigation


sealed class Screen {
    object Splash : Screen()
    object Login : Screen()
    object Home : Screen()
}
