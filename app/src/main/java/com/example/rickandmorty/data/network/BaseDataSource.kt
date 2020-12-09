package com.example.rickandmorty.data.network

import com.bumptech.glide.load.engine.Resource
import com.example.rickandmorty.utils.Resource.Companion.success
import retrofit2.Response


abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): com.example.rickandmorty.utils.Resource<T>
    {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return com.example.rickandmorty.utils.Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): com.example.rickandmorty.utils.Resource<T>
    {
        return com.example.rickandmorty.utils.Resource.error("Network call has failed for a following reason: $message")
    }

}