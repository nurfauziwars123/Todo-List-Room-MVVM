package com.example.submission8.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.submission8.database.model.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getData() : List<User>

    @Query("SELECT * FROM user WHERE email = :email")
    fun getDataEmail(email : String) : User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user : User)

    @Query("SELECT * FROM user WHERE email = :email OR name = :name")
    fun getDataUserExist(email : String, name : String) : User
}