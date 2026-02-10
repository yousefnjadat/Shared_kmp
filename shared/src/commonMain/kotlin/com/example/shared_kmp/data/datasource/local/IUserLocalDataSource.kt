package com.example.shared_kmp.data.datasource.local

import com.example.shared_kmp.common.local.entities.UserEntity

interface IUserLocalDataSource {
    suspend fun saveUser(user: UserEntity) // Changed from String
    suspend fun getUser(): UserEntity?     // Changed from String
    suspend fun deleteUser()
    fun isUserLoggedIn(): Boolean
}