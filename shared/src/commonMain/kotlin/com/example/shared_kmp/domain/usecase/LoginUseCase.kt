package com.example.shared_kmp.domain.usecase

import com.example.shared_kmp.domain.model.LoginRequest
import com.example.shared_kmp.domain.repository.LoginRepository

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(request: LoginRequest) = repository.login(request)
    suspend fun getSavedUser() = repository.getSavedUser()
    fun logout() = repository.clearSession()
}