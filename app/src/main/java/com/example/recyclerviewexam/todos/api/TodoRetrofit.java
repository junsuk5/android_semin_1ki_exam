package com.example.recyclerviewexam.todos.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodoRetrofit {
    public static TodoApi create() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TodoApi.BASE_URL)
                .build();
        return retrofit.create(TodoApi.class);
    }
}
