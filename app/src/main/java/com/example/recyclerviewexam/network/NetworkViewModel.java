package com.example.recyclerviewexam.network;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkViewModel extends ViewModel {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private JsonplaceHolderService service = retrofit.create(JsonplaceHolderService.class);

    MutableLiveData<List<Photo>> photoList = new MutableLiveData<>();

    MutableLiveData<Boolean> isProgressing = new MutableLiveData<>();


    public void fetch() {
        isProgressing.setValue(true);

        service.listPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                // 응답
                photoList.setValue(response.body());
                isProgressing.setValue(false);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                // 실패
                isProgressing.setValue(false);
            }
        });
    }


}
