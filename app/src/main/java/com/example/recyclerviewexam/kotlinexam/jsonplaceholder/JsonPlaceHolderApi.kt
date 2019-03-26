package com.example.recyclerviewexam.kotlinexam.jsonplaceholder

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class User(val id: Int, val name: String, val username: String)

interface JsonPlaceHolderApi {
    @GET("users")
    fun getUsers(): Call<List<User>>

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): JsonPlaceHolderApi {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(JsonPlaceHolderApi::class.java)
        }
    }
}