package com.core.kamalpagging.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.core.kamalpagging.data.model.User

@Dao
interface UserDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Insert
    suspend fun insertUser(users:User) : Long


     // For Live data not need suspend() and any background thread , it automatic work background
     @Query("Select * from user_data_table")
     suspend fun getAllTopic() : List<User>
}