package com.example.to_do_list.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDAO {
    @Insert
    fun insertToDo(toDo: ToDo)

    @Update
    fun updateToDo(toDo: ToDo)

    @Query("SELECT * FROM ToDoList")
    fun getAllToDoList() : LiveData<List<ToDo>>

    @Delete
    fun delete(toDo: ToDo)
}