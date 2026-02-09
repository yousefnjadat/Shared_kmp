package com.example.shared_kmp.data.repository

import com.example.shared_kmp.common.Result
import com.example.shared_kmp.data.datasource.remote.ILoginDataSource
import com.example.shared_kmp.data.dto.LoginResponseDto
import com.example.shared_kmp.data.mapper.LoginMapper
import com.example.shared_kmp.domain.model.LoginRequest
import com.example.shared_kmp.domain.model.LoginResponse
import com.example.shared_kmp.domain.repository.LoginRepository
import io.ktor.client.call.body


class LoginRepositoryImpl(
    private val iLoginDataSource: ILoginDataSource,
    private val mapper: LoginMapper
) : LoginRepository {

    override suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return try {
            val loginResponseDto: LoginResponseDto =
                iLoginDataSource.login(mapper.toDto(request)).body()

            if (loginResponseDto.status == true) {
                Result.Success(mapper.toDomain(loginResponseDto))
            } else {
                Result.Error(
                    Exception(loginResponseDto.resultMessage ?: "Login failed")
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}