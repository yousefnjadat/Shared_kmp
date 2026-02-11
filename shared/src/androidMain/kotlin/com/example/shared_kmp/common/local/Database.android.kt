package com.example.shared_kmp.common.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

// shared/src/androidMain/kotlin/Database.android.kt

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}