package com.example.recyclerviewexam.todos.api;

import com.example.recyclerviewexam.todos.models.Todo;
import com.example.recyclerviewexam.todos.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TodoApi {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("users")
    Call<List<User>> getUsers();

    @GET("todos")
    Call<List<Todo>> getTodos(@Query("userId") int userId);
}
