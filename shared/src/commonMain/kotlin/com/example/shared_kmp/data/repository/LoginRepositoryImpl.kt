package com.example.shared_kmp.data.repository

import com.example.shared_kmp.common.Result
import com.example.shared_kmp.data.datasource.remote.LoginApi
import com.example.shared_kmp.data.mapper.LoginMapper
import com.example.shared_kmp.domain.model.LoginRequest
import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.domain.repository.LoginRepository


class LoginRepositoryImpl(
    private val loginApi: LoginApi,
    private val mapper: LoginMapper,
) : LoginRepository {

    override suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return when (val result = loginApi.login(mapper.toDto(request))) {
            is Result.Success -> Result.Success(mapper.toDomain(result.data))
            else -> result as Result<LoginResponse>
        }
    }
}