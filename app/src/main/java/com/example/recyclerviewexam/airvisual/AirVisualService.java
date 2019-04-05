package com.example.recyclerviewexam.airvisual;

import com.example.recyclerviewexam.models.NearestCity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AirVisualService {
    String BASE_URL = "https://api.airvisual.com/";

    @GET("v2/nearest_city?key=YKK6AtsGKLrRZyJht")
    Call<NearestCity> getNearestCity();
}
