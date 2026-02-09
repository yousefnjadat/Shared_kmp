package com.example.shared_kmp.data.datasource.remote


import com.example.shared_kmp.data.dto.LoginRequestDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*

class LoginDataSourceImpl(private val client: HttpClient) : ILoginDataSource {

    override suspend fun login(request: LoginRequestDto): HttpResponse {
        return client.post("Login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }
}