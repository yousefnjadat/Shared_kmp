package com.example.shared_kmp.data.repository

import com.example.shared_kmp.data.datasource.local.IUserLocalDataSource
import com.example.shared_kmp.data.mapper.LoginMapper
import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.domain.repository.IUserRepository

class UserRepositoryImpl(
    private val dataSource: IUserLocalDataSource,
    private val mapper: LoginMapper
) : IUserRepository {

    override suspend fun saveUser(response: LoginResponse) {
        val entity = mapper.toEntity(response)
        dataSource.saveUser(entity)
    }

    override suspend fun getUser(): LoginResponse? {
        if (!dataSource.isUserLoggedIn()) return null
        val entity = dataSource.getUser() ?: return null
        return mapper.fromEntity(entity)
    }

    override suspend fun deleteUser() {
        dataSource.deleteUser()
    }
}