package com.example.shared_kmp.domain.repository

import com.example.shared_kmp.domain.model.LoginResponse

interface IUserRepository {
    suspend fun saveUser(response: LoginResponse) // Change parameter type
    suspend fun getUser(): LoginResponse?
    suspend fun deleteUser()
}