package com.example.to_do_list.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list.databinding.LayoutRecyclerBinding
import com.example.to_do_list.room.ToDo
import com.example.to_do_list.room.ToDoDatabase

class RecyclerViewAdapter(toDoList : ArrayList<ToDo>, onClickListener: OnClickListener, context : Context) : RecyclerView.Adapter<ViewHolder>() {
    private var toDoList: ArrayList<ToDo>
    private var onClickListener : OnClickListener
    private var context : Context
    init {
        this.toDoList = toDoList
        this.onClickListener = onClickListener
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = LayoutRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = toDoList[position]
        holder.bindData(model)
        holder.getViewBinding().itemLayout.setOnClickListener {
            onClickListener.onClickListener(model)
        }
        holder.getViewBinding().itemLayout.setOnLongClickListener { view ->
            val dialogBuilder = AlertDialog.Builder(view?.context)
            dialogBuilder.setMessage("Do you want to delete this To Do item ?")
                .setTitle("Confirm")
                .setPositiveButton(
                    "Yes"
                ) { p0, p1 ->
                    ToDoDatabase.getInstance(view?.context).todoDao().delete(model)
                    toDoList.remove(model)
                    notifyDataSetChanged()
                }
                .setNegativeButton("No") { p0, p1 ->
                    p0.cancel()
                }
                .show()
            false
        }
        holder.getViewBinding().status.setOnClickListener {
            model.setStatus(holder.getViewBinding().status.isChecked)
            ToDoDatabase.getInstance(context).todoDao().updateToDo(model)
            notifyDataSetChanged()
        }
    }
    interface OnClickListener{
        fun onClickListener(toDo: ToDo)
    }
}
class ViewHolder(binding : LayoutRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
    private val viewBinding : LayoutRecyclerBinding
    init {
        this.viewBinding = binding
    }
    fun bindData(toDo: ToDo){
        viewBinding.content.text = toDo.getContent()
        viewBinding.status.isChecked = toDo.getStatus()
    }
    fun getViewBinding(): LayoutRecyclerBinding{
        return viewBinding
    }
}