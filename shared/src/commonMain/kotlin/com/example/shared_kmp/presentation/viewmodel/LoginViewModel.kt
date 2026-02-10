package com.example.shared_kmp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared_kmp.common.Result
import com.example.shared_kmp.domain.model.LoginRequest
import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.domain.usecase.LoginUseCase
import com.example.shared_kmp.domain.usecase.UserUseCase
import com.example.shared_kmp.navigation.NavigationManager
import com.example.shared_kmp.navigation.Screens
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val navigationManager: NavigationManager,
    private val loginUseCase: LoginUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Init)
    val loginState = _loginState.asStateFlow()

    fun login(userId: String, password: String) {
        if (_loginState.value is Result.Loading) return

        viewModelScope.launch {
            _loginState.value = Result.Loading

            try {
                val request = LoginRequest(
                    userId = userId,
                    password = password,
                    language = 0, imei = "test", version = "1.0", deviceToken = "test"
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
