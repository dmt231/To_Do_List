package com.example.to_do_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.to_do_list.room.ToDo
import com.example.to_do_list.room.ToDoDatabase
import com.example.to_do_list.databinding.LayoutDetailFragmentBinding

class DetailFragment : Fragment() {
    private lateinit var viewBinding : LayoutDetailFragmentBinding
    private lateinit var toDoDatabase : ToDoDatabase
    private var toDo : ToDo? = null
    private var type : String = "Create"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = LayoutDetailFragmentBinding.inflate(inflater, container, false)
        toDoDatabase = ToDoDatabase.getInstance(requireContext())
        viewBinding.done.setOnClickListener {
            done()
        }
        viewBinding.back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        viewBinding.detailFragmentLayout.setOnClickListener{
            //DO NOTHING
        }
        getData()
        return viewBinding.root
    }
    private fun done() {
        when(type){
            "Create" ->{
                if(viewBinding.editContent.text.toString().isNotEmpty()){
                    val toDo = ToDo(viewBinding.editContent.text.toString(), viewBinding.checkBox.isChecked)
                    toDoDatabase.todoDao().insertToDo(toDo)
                    Toast.makeText(requireContext(), "Insert Success", Toast.LENGTH_SHORT).show()
                }
            }
            "Edit" ->{
                if(viewBinding.editContent.text.toString().isNotEmpty()){
                    toDo!!.setContent(viewBinding.editContent.text.toString())
                    toDo!!.setStatus(viewBinding.checkBox.isChecked)
                    toDoDatabase.todoDao().updateToDo(toDo!!)
                    Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun getData(){
        val bundle = arguments
        if(bundle != null){
            type = "Edit"
            this.toDo = bundle["ToDo"] as ToDo
            viewBinding.editContent.setText(toDo!!.getContent())
            viewBinding.checkBox.isChecked = toDo!!.getStatus()
        }
    }
}