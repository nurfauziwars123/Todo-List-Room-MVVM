package com.example.submission8.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int? = null,

    @ColumnInfo(name = "name")
    var name : String? = null,

    @ColumnInfo(name = "email")
    var email : String? = null,

    @ColumnInfo(name = "password")
    var password : String? = null
)