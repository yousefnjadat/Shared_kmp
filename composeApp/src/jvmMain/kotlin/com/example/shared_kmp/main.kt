package com.example.shared_kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.shared_kmp.di.initKoinJvm
import org.koin.core.context.GlobalContext

fun main() = application {
    // Initialize Koin only once
    if (GlobalContext.getOrNull() == null) {
        initKoinJvm()
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Shared_kmp",
    ) {
        App()
    }
}