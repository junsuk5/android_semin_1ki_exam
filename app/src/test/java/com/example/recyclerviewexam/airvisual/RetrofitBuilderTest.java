package com.example.recyclerviewexam.airvisual;

import com.example.recyclerviewexam.models.CityResult;
import com.example.recyclerviewexam.models.CountryResult;
import com.example.recyclerviewexam.models.StateResult;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Retrofit;

public class RetrofitBuilderTest {
    @Before
    public void setUp() throws Exception {
        // 초기화
    }

    @After
    public void tearDown() throws Exception {
        // 끝나고
    }

    /**
     * 1.
     * 2.
     * 3.
     */
    @Test
    public void getInstance() {
        Retrofit retrofit = RetrofitBuilder.getInstance(AirVisualService.BASE_URL);

        Assert.assertNotNull(retrofit);

        Retrofit retrofit1 = RetrofitBuilder.getInstance(AirVisualService.BASE_URL);

        Assert.assertNotNull(retrofit1);

        Assert.assertEquals(retrofit, retrofit1);
    }

    @Test
    public void getCountries() throws IOException {
        Retrofit retrofit = RetrofitBuilder.getInstance(AirVisualService.BASE_URL);

        AirVisualService service = retrofit.create(AirVisualService.class);

        CountryResult result = service.getCountries().execute().body();

        Assert.assertEquals("success", result.getStatus());
        Assert.assertEquals("Afghanistan", result.getData().get(0).getCountry());
    }

    @Test
    public void getStates() throws IOException {
        Retrofit retrofit = RetrofitBuilder.getInstance(AirVisualService.BASE_URL);

        AirVisualService service = retrofit.create(AirVisualService.class);

        StateResult result = service.getStates("South Korea").execute().body();

        Assert.assertEquals("success", result.getStatus());
        Assert.assertEquals("Chungcheongbuk-do", result.getData().get(0).getState());
    }

    @Test
    public void getCities() throws IOException {
        Retrofit retrofit = RetrofitBuilder.getInstance(AirVisualService.BASE_URL);

        AirVisualService service = retrofit.create(AirVisualService.class);

        CityResult result = service.getCities("South Korea", "Chungcheongbuk-do").execute().body();

        Assert.assertEquals("success", result.getStatus());
        Assert.assertEquals("Cheongju", result.getData().get(0).getCity());
    }
}