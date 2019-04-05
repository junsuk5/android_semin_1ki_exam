package com.example.recyclerviewexam.airvisual;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static Retrofit sRetrofit;

    public static Retrofit getInstance(String baseUrl) {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
        }
        return sRetrofit;
    }
}
