package com.example.recyclerviewexam.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonplaceHolderService {
    @GET("photos")
    Call<List<Photo>> listPhotos();
}
