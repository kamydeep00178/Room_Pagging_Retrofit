package com.core.kamalpagging.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data_table")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "email")
    val email:String,
    @ColumnInfo(name = "first_name")
    val first_name:String,
    @ColumnInfo(name = "last_name")
    val last_name:String,
    @ColumnInfo(name = "avatar")
    val avatar : String
    )
