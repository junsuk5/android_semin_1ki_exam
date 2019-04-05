package com.example.recyclerviewexam.airvisual;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}