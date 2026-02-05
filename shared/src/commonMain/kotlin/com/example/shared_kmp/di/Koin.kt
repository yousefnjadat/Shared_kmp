package com.example.shared_kmp.di

import com.example.shared_kmp.data.datasource.remote.ApiClient
import com.example.shared_kmp.data.datasource.remote.LoginApi
import com.example.shared_kmp.data.datasource.remote.LoginApiImpl
import com.example.shared_kmp.data.mapper.LoginMapper
import com.example.shared_kmp.data.repository.LoginRepositoryImpl
import com.example.shared_kmp.domain.repository.LoginRepository
import com.example.shared_kmp.domain.usecase.LoginUseCase
import com.example.shared_kmp.presentation.viewmodel.HomeViewModel
import com.example.shared_kmp.presentation.viewmodel.LoginViewModel
import io.ktor.client.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import com.russhwolf.settings.Settings

val appModule = module {
    single { Settings() }
    single<HttpClient> { ApiClient.create() }
    single<LoginApi> { LoginApiImpl(get(), get()) }
    single { LoginMapper() }

    // Pass the 3 arguments: Api, Mapper, Settings
    single<LoginRepository> { LoginRepositoryImpl(get(), get(), get()) }

    single { LoginUseCase(get()) }

    // ViewModels
    factory { LoginViewModel(get()) }
    factory { HomeViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}