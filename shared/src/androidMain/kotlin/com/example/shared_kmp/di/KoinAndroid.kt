package com.example.shared_kmp.di

import android.content.Context
import com.example.shared_kmp.common.local.getDatabaseBuilder
import com.example.shared_kmp.common.local.getRoomDatabase
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoinAndroid(context: Context) {
    startKoin {
        modules(
            appModule,
            androidDatabaseModule(context)
        )
    }
}

fun androidDatabaseModule(context: Context) = module {
    single {
        getRoomDatabase(
            getDatabaseBuilder(context)
        )
    }
}