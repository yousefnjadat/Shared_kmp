package com.example.shared_kmp.domain.usecase

import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.domain.repository.IUserRepository

class UserUseCase(private val repository: IUserRepository) {
    suspend fun saveUser(response: LoginResponse) {
        return repository.saveUser(response)
    }

    suspend fun getUser(): LoginResponse? {
        return repository.getUser()
    }

    suspend fun deleteUser() {
        return repository.deleteUser()
    }
}