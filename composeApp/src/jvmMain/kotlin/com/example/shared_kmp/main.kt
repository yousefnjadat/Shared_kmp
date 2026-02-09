package com.example.shared_kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.shared_kmp.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Shared_kmp",
    ) {
        App()
    }
}