package com.example.to_do_list.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.to_do_list.repository.ToDoRepository
import com.example.to_do_list.room.ToDo

class ToDoViewModel() : ViewModel() {
    private lateinit var toDoRepository: ToDoRepository
    private lateinit var liveDataToDoList: LiveData<List<ToDo>>

    fun getLiveDataNote(context: Context): LiveData<List<ToDo>> {
        toDoRepository = ToDoRepository()
        liveDataToDoList = toDoRepository.getAllToDoList(context)
        return liveDataToDoList
    }
}
