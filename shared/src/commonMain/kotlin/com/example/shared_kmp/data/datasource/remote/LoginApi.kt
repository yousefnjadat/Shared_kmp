package com.example.shared_kmp.data.datasource.remote

import com.example.shared_kmp.common.Result
import com.example.shared_kmp.data.dto.LoginRequestDto
import com.example.shared_kmp.data.dto.LoginResponseDto

interface LoginApi {
    suspend fun login(request: LoginRequestDto): Result<LoginResponseDto>
}