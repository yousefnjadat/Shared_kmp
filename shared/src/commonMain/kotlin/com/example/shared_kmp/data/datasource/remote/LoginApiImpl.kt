package com.example.shared_kmp.data.datasource.remote

import com.example.shared_kmp.common.Result
import com.example.shared_kmp.data.datasource.local.UserLocalDataSource
import com.example.shared_kmp.data.dto.LoginRequestDto
import com.example.shared_kmp.data.dto.LoginResponseDto
import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoginApiImpl(
    private val client: HttpClient,
    private val userLocalDataSource: UserLocalDataSource,
    private val settings: Settings
) : LoginApi {

    override suspend fun login(request: LoginRequestDto): Result<LoginResponseDto> {
        return try {
            val response = client.post("Login") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            val loginResponse: LoginResponseDto = response.body()
            println("API LoginResponseDto: $loginResponse")


            if (loginResponse.status == true) {
                val json = Json { ignoreUnknownKeys = true }
                val jsonString = json.encodeToString(loginResponse)
                userLocalDataSource.saveUser(jsonString)
                Result.Success(loginResponse)
            } else {
                Result.Error(
                    Exception(
                        loginResponse.resultMessage?.ifEmpty { "Login failed" }
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}