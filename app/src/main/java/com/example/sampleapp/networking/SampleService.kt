package com.example.sampleapp.networking

import com.example.sampleapp.model.ToDo
import retrofit2.Response
import retrofit2.http.GET

interface SampleService {
    @GET("todos")
    suspend fun getToDos(): Response<List<ToDo>>
}