package com.example.shared_kmp.data.datasource.local

import com.example.shared_kmp.data.dto.LoginResponseDto
import com.example.shared_kmp.data.mapper.LoginMapper
import com.example.shared_kmp.domain.model.LoginResponse
import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json

class UserLocalDataSource(
    private val settings: Settings
) {

    private val userKey = "login_response_json"

    fun saveUser(userJsonString: String) {
        settings.putString(userKey, userJsonString)
    }

    fun getUser(): LoginResponse? {
        val jsonString = settings.getStringOrNull(userKey) ?: return null
        return if (jsonString.isNotEmpty()) {
            try {
                val dto = Json.decodeFromString<LoginResponseDto>(jsonString)
                LoginMapper().toDomain(dto)
            } catch (e: Exception) {
                null
            }
        } else null
    }

    fun deleteUser() {
        settings.remove(userKey)
    }
}
