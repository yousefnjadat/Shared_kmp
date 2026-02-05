package com.example.shared_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform