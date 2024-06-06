package com.example.to_do_list.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ToDoList")
class ToDo(content: String, status : Boolean) : java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    private var id: Int? = 0
    private var content : String? = content
    private var status : Boolean? = status
    fun getId(): Int {
        return id!!
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getContent() : String{
        return content!!
    }
    fun setContent(content : String){
        this.content = content
    }
    fun setStatus(status : Boolean){
        this.status = status
    }
    fun getStatus():Boolean{
        return status!!
    }
}