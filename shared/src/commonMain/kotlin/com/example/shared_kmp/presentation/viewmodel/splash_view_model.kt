package com.example.shared_kmp.presentation.viewmodel

import com.example.shared_kmp.data.dto.LoginResponseDto
import com.example.shared_kmp.data.mapper.LoginMapper
import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.navigation.NavigationManager
import com.example.shared_kmp.navigation.Screens
import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json

class SplashViewModel(
    private val settings: Settings,
    private val navigationManager: NavigationManager
) {

    init {
        checkAuth()
    }

    fun checkAuth() {
        val user: LoginResponse? = getUser()

        if (user != null && user.accessToken.isNotEmpty()) {
            navigationManager.navigateTo(Screens.Home)
        } else {
            navigationManager.navigateTo(Screens.Login)
        }
    }


    fun getUser(): LoginResponse? {
        val jsonString: String = settings.getString("login_response_json", "")
        return if (jsonString.isNotEmpty()) {
            try {
                val dto = Json.decodeFromString<LoginResponseDto>(jsonString)
                val user: LoginResponse = LoginMapper().toDomain(dto)
                user
            } catch (e: Exception) {
                null
            }
        } else null
    }
}