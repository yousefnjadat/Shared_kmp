package com.example.shared_kmp.data.mapper

import com.example.shared_kmp.data.dto.LoginRequestDto
import com.example.shared_kmp.data.dto.LoginResponseDto
import com.example.shared_kmp.domain.model.LoginRequest
import com.example.shared_kmp.domain.model.LoginResponse

class LoginMapper {
    fun toDto(domain: LoginRequest): LoginRequestDto = LoginRequestDto(
        userId = domain.userId,
        password = domain.password,
        language = domain.language,
        imei = domain.imei,
        version = domain.version,
        deviceToken = domain.deviceToken
    )

    fun toDomain(dto: LoginResponseDto): LoginResponse = LoginResponse(
        userId = dto.user?.userId ?: "",
        userName = dto.user?.userName ?: "",
        accessToken = dto.accessToken ?: "",
        status = dto.status ?: false
    )
}