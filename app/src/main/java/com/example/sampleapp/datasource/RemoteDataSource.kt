package com.example.sampleapp.datasource

import com.example.sampleapp.model.ToDo
import com.example.sampleapp.networking.SampleService
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor( private val sampleService: SampleService) {

    suspend fun getToDo() : Response<List<ToDo>> {
        return sampleService.getToDos()
    }
}