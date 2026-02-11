// shared/src/jvmMain/kotlin/com/example/shared_kmp/di/KoinJvm.kt
package com.example.shared_kmp.di

import com.example.shared_kmp.common.local.getDatabaseBuilder
import com.example.shared_kmp.common.local.getRoomDatabase
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoinJvm() {
    startKoin {
        modules(
            appModule,
            jvmDatabaseModule()
        )
    }
}

fun jvmDatabaseModule() = module {
    single {
        getRoomDatabase(
            getDatabaseBuilder()
        )
    }
}