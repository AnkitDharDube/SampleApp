package com.example.sampleapp.base

sealed class NetworkResult<T>( val data :T? = null, val message : String? = null) {
    class Sucesss<T>(data :T): NetworkResult<T>(data)

    class Error<T>(message: String, data: T? = null): NetworkResult<T>(data,message)

    class Loading<T>: NetworkResult<T>()

}