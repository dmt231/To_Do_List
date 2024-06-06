package com.example.to_do_list.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ToDo::class], version = 1)
 abstract class ToDoDatabase : RoomDatabase(){
    companion object {

        @Volatile
        private var instance: ToDoDatabase? = null

        @Synchronized
        fun getInstance(context: Context?): ToDoDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, ToDoDatabase::class.java, "ToDoDatabase.db")
                    .allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
    abstract fun todoDao() : ToDoDAO
}