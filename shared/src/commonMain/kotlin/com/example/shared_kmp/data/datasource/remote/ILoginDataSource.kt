package com.example.shared_kmp.data.datasource.remote

import com.example.shared_kmp.data.dto.LoginRequestDto
import io.ktor.client.statement.HttpResponse

interface ILoginDataSource {
    suspend fun login(request: LoginRequestDto): HttpResponse
}