package com.example.shared_kmp.common.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shared_kmp.common.local.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE id = 0")
    suspend fun getUser(): UserEntity?

    @Query("DELETE FROM user_table")
    suspend fun deleteUser()
}