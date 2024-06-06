package com.example.to_do_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.adapter.RecyclerViewAdapter
import com.example.to_do_list.viewModel.ToDoViewModel
import com.example.to_do_list.databinding.LayoutMainFragmentBinding
import com.example.to_do_list.room.ToDo

class MainFragment : Fragment(){
    private lateinit var viewBinding : LayoutMainFragmentBinding
    private lateinit var toDoViewModel : ToDoViewModel
    private lateinit var adapter: RecyclerViewAdapter
    private var toDoList : ArrayList<ToDo>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = LayoutMainFragmentBinding.inflate(inflater, container, false)
        viewBinding.add.setOnClickListener {
            onChangeToAdd()
        }
        toDoList = ArrayList()
        toDoViewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        setUpRecyclerView()
        getData()
        return viewBinding.root
    }

    private fun onChangeToAdd() {
        val detailFragment = DetailFragment()
        val fragmentTrans = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.mainActivity, detailFragment)
        fragmentTrans.addToBackStack(detailFragment.tag)
        fragmentTrans.commit()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getData(){
        toDoViewModel.getLiveDataNote(requireContext()).observe(viewLifecycleOwner){
            if(toDoList!!.isNotEmpty()){
                toDoList!!.clear()
                for(note in it){
                    toDoList!!.add(note)
                }
            }
            else{
                for(note in it){
                    toDoList!!.add(note)
                }
            }
            adapter.notifyDataSetChanged()
        }
    }


    private fun setUpRecyclerView(){
        viewBinding.recyclerView.setHasFixedSize(false)
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = RecyclerViewAdapter(toDoList!!, object : RecyclerViewAdapter.OnClickListener{
            override fun onClickListener(toDo: ToDo) {
                changeToDetailNote(toDo)
            }
        }, requireContext())
        viewBinding.recyclerView.layoutManager = layoutManager
        viewBinding.recyclerView.adapter = adapter
    }

    private fun changeToDetailNote(toDo: ToDo) {
        val detailFragment = DetailFragment()
        val fragmentTrans = requireActivity().supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putSerializable("ToDo", toDo)
        detailFragment.arguments = bundle
        fragmentTrans.add(R.id.mainActivity, detailFragment)
        fragmentTrans.addToBackStack(detailFragment.tag)
        fragmentTrans.commit()
    }
}