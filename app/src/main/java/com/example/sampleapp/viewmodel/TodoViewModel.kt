package com.example.sampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.base.NetworkResult
import com.example.sampleapp.model.ToDo
import com.example.sampleapp.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: ToDoRepository): ViewModel() {
    private var _todo : MutableLiveData<NetworkResult<List<ToDo>>> = MutableLiveData()
    val todo : LiveData<NetworkResult<List<ToDo>>> = _todo

    fun fetchToDo(){
        viewModelScope.launch {
            repository.getToDo().collect { response ->
                _todo.value = response
            }
        }
    }

}