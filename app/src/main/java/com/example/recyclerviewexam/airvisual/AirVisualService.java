package com.example.recyclerviewexam.airvisual;

import com.example.recyclerviewexam.models.CityResult;
import com.example.recyclerviewexam.models.CountryResult;
import com.example.recyclerviewexam.models.NearestCity;
import com.example.recyclerviewexam.models.StateResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AirVisualService {
    String BASE_URL = "https://api.airvisual.com/";

    /**
     * 근처 도시 정보
     * @return
     */
    @GET("v2/nearest_city?key=YKK6AtsGKLrRZyJht")
    Call<NearestCity> getNearestCity();

    /**
     * 지원되는 도시 리스트
     * @return
     */
    @GET("v2/countries?key=YKK6AtsGKLrRZyJht")
    Call<CountryResult> getCountries();


    /**
     * 주 리스트
     * @param country 나라
     * @return 주 리스트
     */
    @GET("v2/states?key=YKK6AtsGKLrRZyJht")
    Call<StateResult> getStates(@Query("country") String country);

    /**
     * 도시 리스트
     * @param country 나라
     * @param state 주
     * @return 도시 리스트
     */
    @GET("v2/cities?key=YKK6AtsGKLrRZyJht")
    Call<CityResult> getCities(@Query("country") String country,
                               @Query("state") String state);
}
