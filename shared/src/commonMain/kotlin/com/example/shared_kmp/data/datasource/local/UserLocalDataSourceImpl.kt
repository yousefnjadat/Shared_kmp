package com.example.shared_kmp.data.datasource.local

import com.example.shared_kmp.userKey
import com.russhwolf.settings.Settings

class UserLocalDataSourceImpl(private val settings: Settings) : IUserLocalDataSource {
    override suspend fun saveUser(userJsonString: String) {
        settings.putString(userKey, userJsonString)
    }

    override suspend fun getUser(): String? {
        return settings.getStringOrNull(userKey)
    }

    override suspend fun deleteUser() {
        settings.remove(userKey)
    }
}
