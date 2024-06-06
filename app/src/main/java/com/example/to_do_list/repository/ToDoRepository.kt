package com.example.to_do_list.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.to_do_list.room.ToDo
import com.example.to_do_list.room.ToDoDatabase

class ToDoRepository {
    fun getAllToDoList(context : Context) : LiveData<List<ToDo>> {
        return ToDoDatabase.getInstance(context).todoDao().getAllToDoList()
    }
}
