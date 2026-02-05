package com.example.shared_kmp.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SplashScreen() {
    // Simple full-screen splash
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        androidx.compose.material3.CircularProgressIndicator()
        // Optional: Add App Logo here
        // Image(painterResource(id = R.drawable.ic_logo), contentDescription = null)
    }
}