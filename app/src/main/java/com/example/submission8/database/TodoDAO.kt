package com.example.submission8.database

import androidx.room.*
import com.example.submission8.database.model.Todo
import com.example.submission8.database.model.User


@Dao
interface TodoDAO {

    @Query("SELECT * FROM todo")
    fun getTodo() : List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todo : Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTodo(todo : Todo)

    @Delete
    fun deleteTodo(todo : Todo)
}