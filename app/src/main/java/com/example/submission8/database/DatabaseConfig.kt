package com.example.submission8.database

import android.content.Context
import androidx.room.*
import com.example.submission8.database.model.Todo
import com.example.submission8.database.model.User

@Database(entities = arrayOf(Todo::class, User::class), version = 2, exportSchema = false)
abstract class DatabaseConfig : RoomDatabase() {

    abstract fun todoDao(): TodoDAO
    abstract fun userDao(): UserDAO

    companion object {
        private var INSTANCE: DatabaseConfig? = null

        fun getInstance(context: Context): DatabaseConfig? {
            if (INSTANCE == null) {
                synchronized(DatabaseConfig::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseConfig::class.java, "dbtodo.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}