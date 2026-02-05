package com.example.shared_kmp.presentation.viewmodel

import com.example.shared_kmp.common.Result
import com.example.shared_kmp.domain.model.LoginRequest
import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.domain.usecase.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Init)
    val loginState = _loginState.asStateFlow()

    init {
        checkUserSession()
    }

    private fun checkUserSession() {
        coroutineScope.launch {
            val savedUser = loginUseCase.getSavedUser()
            if (savedUser != null) {
                _loginState.value = Result.Success(savedUser)
            }
        }
    }

    suspend fun getSavedUserDirectly(): LoginResponse? {
        return loginUseCase.getSavedUser()
    }

    fun logout() {
        loginUseCase.logout()
        _loginState.value = Result.Init
    }

    private var loginJob: Job? = null

    fun login(userId: String, password: String) {
        loginJob?.cancel()
        _loginState.value = Result.Loading

        loginJob = coroutineScope.launch {
            val request = LoginRequest(
                userId = userId,
                password = password,
                language = 0,
                imei = "test_device",
                version = "1.0.0",
                deviceToken = "test_token"
            )
            val result = loginUseCase(request)
            if (result is Result.Success) {
    
            }
            _loginState.value = result
        }
    }


    fun clearState() {
        _loginState.value = Result.Init
    }
}
