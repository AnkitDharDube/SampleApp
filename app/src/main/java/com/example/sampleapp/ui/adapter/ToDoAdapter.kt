package com.example.sampleapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.sampleapp.databinding.ItemTodoBinding
import com.example.sampleapp.model.ToDo

class ToDoAdapter : RecyclerView.Adapter<ToDoViewHolder>() {
    var todoList : List<ToDo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context))
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.binding(todo)
    }

}

class ToDoViewHolder(private val itemBinding : ItemTodoBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun binding(toDo: ToDo) {
        itemBinding.status.text = toDo.completed.toString()
        itemBinding.title.text = toDo.title
        itemBinding.userId.text= toDo.userId
    }

}