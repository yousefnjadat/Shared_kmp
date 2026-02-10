package com.example.shared_kmp.common.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey val id: Int = 0, // Hardcoded to 0 to ensure we only ever have one user
    val userId: String,
    val userName: String,
    val accessToken: String,
    val status: Boolean
)