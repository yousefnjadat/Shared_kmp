package com.example.shared_kmp.data.datasource.local

interface IUserLocalDataSource {
    suspend fun saveUser(userJsonString: String)
    suspend fun getUser(): String?
    suspend fun deleteUser()
}