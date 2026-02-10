package com.example.shared_kmp.data.datasource.local

import com.example.shared_kmp.common.local.dao.UserDao
import com.example.shared_kmp.common.local.entities.UserEntity
import com.example.shared_kmp.loggedInKey
import com.example.shared_kmp.userKey
import com.russhwolf.settings.Settings

class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val settings: Settings
) : IUserLocalDataSource {

    override suspend fun saveUser(user: UserEntity) {
        userDao.insertUser(user) // Save to Room
        settings.putBoolean(loggedInKey, true) // Save flag to Settings
    }

    override suspend fun getUser(): UserEntity? {
        return userDao.getUser()
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
        settings.remove(loggedInKey)
    }

    override fun isUserLoggedIn(): Boolean = settings.getBoolean(loggedInKey, false)
}
