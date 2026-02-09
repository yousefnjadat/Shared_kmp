package com.example.shared_kmp.presentation.viewmodel

import com.example.shared_kmp.common.Result
import com.example.shared_kmp.domain.model.LoginRequest
import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.domain.usecase.LoginUseCase
import com.example.shared_kmp.domain.usecase.UserUseCase
import com.example.shared_kmp.navigation.NavigationManager
import com.example.shared_kmp.navigation.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val navigationManager: NavigationManager,
    private val loginUseCase: LoginUseCase,
    private val userUseCase: UserUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private var loginJob: Job? = null
    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Init)
    val loginState = _loginState.asStateFlow()

    init {
        // Use launch instead of direct call
        coroutineScope.launch {
            checkUserSession()
        }
    }

    private suspend fun checkUserSession() {
        try {
            val savedUser = userUseCase.getUser()
            if (savedUser != null) {
                _loginState.value = Result.Success(savedUser)
                // Consider auto-navigation here if user is already logged in
                // navigationManager.navigateTo(Screens.Home)
            }
        } catch (e: Exception) {
            println("Error checking user session: ${e.message}")
        }
    }

    fun login(userId: String, password: String) {
        loginJob?.cancel()
        loginJob = coroutineScope.launch {
            _loginState.value = Result.Loading
            try {
                val request = LoginRequest(
                    userId = userId,
                    password = password,
                    language = 0,
                    imei = "test_device",
                    version = "1.0.0",
                    deviceToken = "test_token"
                )
                val result = loginUseCase(request)
                _loginState.value = result
                if (result is Result.Success) {
                    userUseCase.saveUser(result.data)
                    navigationManager.navigateTo(Screens.Home)
                }
            } catch (e: Exception) {
                _loginState.value = Result.Error(e)
            }
        }
    }
}
