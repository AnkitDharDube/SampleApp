package com.example.sampleapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.sampleapp.R
import com.example.sampleapp.base.NetworkResult
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.ui.adapter.ToDoAdapter
import com.example.sampleapp.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val todoViewModel : TodoViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        todoViewModel.fetchToDo()
        binding.todoList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        todoViewModel.todo.observe(this){ networkResult ->
            when(networkResult){
                is NetworkResult.Error -> {
                    binding.todoList.visibility = View.GONE
                    binding.error.text = "Error"
                    binding.error.visibility= View.VISIBLE
                }
                is NetworkResult.Loading -> {
                    binding.todoList.visibility = View.GONE
                    binding.error.text = "Loading ..."
                    binding.error.visibility= View.VISIBLE

                }
                is NetworkResult.Sucesss -> {
                    binding.todoList.visibility = View.VISIBLE
                    binding.error.visibility= View.GONE
                    binding.todoList.adapter = ToDoAdapter().apply { networkResult.data?.let { todoList = it } }
                }
            }
        }



    }
}