package com.example.sampleapp.repository

import com.example.sampleapp.base.BaseApiResponse
import com.example.sampleapp.base.NetworkResult
import com.example.sampleapp.datasource.RemoteDataSource
import com.example.sampleapp.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class ToDoRepository @Inject constructor( private val remoteDataSource: RemoteDataSource) :
    BaseApiResponse() {

        suspend fun getToDo() : Flow<NetworkResult<List<ToDo>>> {
            return flow<NetworkResult<List<ToDo>>>{
                emit(transformAPI { remoteDataSource.getToDo() })
            }.flowOn(Dispatchers.IO)
        }

}