package com.example.shared_kmp.di

import com.example.shared_kmp.common.local.AppDatabase
import com.example.shared_kmp.common.network.ApiClient
import com.example.shared_kmp.data.datasource.local.IUserLocalDataSource
import com.example.shared_kmp.data.datasource.local.UserLocalDataSourceImpl
import com.example.shared_kmp.data.datasource.remote.ILoginDataSource
import com.example.shared_kmp.data.datasource.remote.LoginDataSourceImpl
import com.example.shared_kmp.data.mapper.LoginMapper
import com.example.shared_kmp.data.repository.LoginRepositoryImpl
import com.example.shared_kmp.data.repository.UserRepositoryImpl
import com.example.shared_kmp.domain.repository.IUserRepository
import com.example.shared_kmp.domain.repository.LoginRepository
import com.example.shared_kmp.domain.usecase.LoginUseCase
import com.example.shared_kmp.domain.usecase.UserUseCase
import com.example.shared_kmp.presentation.viewmodel.HomeViewModel
import com.example.shared_kmp.presentation.viewmodel.LoginViewModel
import com.example.shared_kmp.presentation.viewmodel.SplashViewModel
import io.ktor.client.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import com.russhwolf.settings.Settings
import com.example.shared_kmp.navigation.NavigationManager

val appModule = module {
    //main vars
    single { NavigationManager() }
    single { Settings() }
    single<HttpClient> { ApiClient.create() }
    single { LoginMapper() }
    single { get<AppDatabase>().getDao() }

    //DataSourceImpl
    single<ILoginDataSource> { LoginDataSourceImpl(get()) }
    single<IUserLocalDataSource> { UserLocalDataSourceImpl(get(), get()) }

    //RepositoryImpl
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single<IUserRepository> { UserRepositoryImpl(get(), get()) }

    //useCases
    single { UserUseCase(get()) }
    single { LoginUseCase(get()) }

    //viewModels
    factory { LoginViewModel(get(), get(), get()) }
    factory { HomeViewModel(get(), get()) }
    factory { SplashViewModel(get(), get()) } // add navigationManager
}


fun initKoin() {
    startKoin {
        modules(appModule)
    }
}