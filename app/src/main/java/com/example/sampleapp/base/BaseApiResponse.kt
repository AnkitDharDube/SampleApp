package com.example.sampleapp.base

import retrofit2.Response
import java.lang.Exception

abstract class BaseApiResponse {
    suspend fun <T> transformAPI(apiCall : suspend ()-> Response<T>) : NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful){
                val body = response.body()
                body?.let {
                   return NetworkResult.Sucesss(body)
                }
            }
            return NetworkResult.Error("${response.code()} ${response.message()}")
        } catch (e : Exception){
            return NetworkResult.Error(e.message?:e.toString())
        }
    }
}