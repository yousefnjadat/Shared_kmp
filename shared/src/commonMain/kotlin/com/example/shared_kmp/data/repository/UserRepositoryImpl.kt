package com.example.shared_kmp.data.repository

import com.example.shared_kmp.data.datasource.local.IUserLocalDataSource
import com.example.shared_kmp.data.dto.LoginResponseDto
import com.example.shared_kmp.data.mapper.LoginMapper
import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.domain.repository.IUserRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class UserRepositoryImpl(
    private val dataSource: IUserLocalDataSource, private val mapper: LoginMapper
) : IUserRepository {
    override suspend fun saveUser(response: LoginResponse) {
        val dto = mapper.toDto(response)
        val jsonString = Json.encodeToString(dto)
        dataSource.saveUser(jsonString)
    }


    override suspend fun getUser(): LoginResponse? {
        val jsonString = dataSource.getUser() ?: return null
        return if (jsonString.isNotEmpty()) {
            try {
                val dto = Json.decodeFromString<LoginResponseDto>(jsonString)
                LoginMapper().toDomain(dto)
            } catch (e: Exception) {
                null
            }
        } else null
    }

    override suspend fun deleteUser() {
        dataSource.deleteUser()
    }
}