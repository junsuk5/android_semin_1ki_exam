package com.example.recyclerviewexam;

import com.example.recyclerviewexam.airvisual.AirVisualService;
import com.example.recyclerviewexam.models.NearestCity;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTest {
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AirVisualService.BASE_URL)
            .build();
    AirVisualService service = retrofit.create(AirVisualService.class);

    @Test
    public void airVisualTest() throws IOException {
        NearestCity nearestCity = service.getNearestCity().execute().body();

        Assert.assertEquals("success", nearestCity.getStatus());
        Assert.assertEquals("Suwon", nearestCity.getData().getCity());
        Assert.assertEquals(137, nearestCity.getData()
                .getCurrent().getPollution().getAqius());
    }
}
