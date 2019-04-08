package com.example.recyclerviewexam.airvisual;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.databinding.ActivityAirVisualBinding;
import com.example.recyclerviewexam.models.City;
import com.example.recyclerviewexam.models.CityResult;
import com.example.recyclerviewexam.models.Country;
import com.example.recyclerviewexam.models.CountryResult;
import com.example.recyclerviewexam.models.State;
import com.example.recyclerviewexam.models.StateResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AirVisualActivity extends AppCompatActivity {

    private ActivityAirVisualBinding mBinding;
    private AirVisualService mService;

    private ArrayAdapter<Country> mCountryAdapter;
    private ArrayAdapter<State> mStateAdapter;
    private ArrayAdapter<City> mCityAdapter;
    private Country mCountry;
    private State mState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_visual);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_air_visual);

        int color = Color.argb(100, 0, 255, 255);

        mBinding.toolbar.setBackgroundColor(color);

        // Retrofit
        Retrofit retrofit = RetrofitBuilder.getInstance(AirVisualService.BASE_URL);

        mService = retrofit.create(AirVisualService.class);

        // Adapter
        mCountryAdapter = new ArrayAdapter<>(AirVisualActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);
        mBinding.spinnerCountry.setAdapter(mCountryAdapter);
        mStateAdapter = new ArrayAdapter<>(AirVisualActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);
        mBinding.spinnerState.setAdapter(mStateAdapter);
        mCityAdapter = new ArrayAdapter<>(AirVisualActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);
        mBinding.spinnerCity.setAdapter(mCityAdapter);

        // Network Call
        mService.getCountries().enqueue(new Callback<CountryResult>() {
            @Override
            public void onResponse(Call<CountryResult> call, Response<CountryResult> response) {
                CountryResult result = response.body();
                mCountryAdapter.addAll(result.getData());
            }

            @Override
            public void onFailure(Call<CountryResult> call, Throwable t) {

            }
        });

        mBinding.spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 아이템 클릭
                mCountry = mCountryAdapter.getItem(position);
                mService.getStates(mCountry.getCountry()).enqueue(new Callback<StateResult>() {
                    @Override
                    public void onResponse(Call<StateResult> call, Response<StateResult> response) {
                        StateResult result = response.body();

                        if (result == null) {
                            mStateAdapter.clear();
                            mCityAdapter.clear();
                            return;
                        }

                        mStateAdapter.clear();
                        mStateAdapter.addAll(result.getData());

                        mState = mStateAdapter.getItem(0);
                        mService.getCities(mCountry.getCountry(), mState.getState())
                                .enqueue(new Callback<CityResult>() {
                                    @Override
                                    public void onResponse(Call<CityResult> call, Response<CityResult> response) {
                                        CityResult result = response.body();
                                        mCityAdapter.clear();
                                        mCityAdapter.addAll(result.getData());
                                    }

                                    @Override
                                    public void onFailure(Call<CityResult> call, Throwable t) {

                                    }
                                });
                    }

                    @Override
                    public void onFailure(Call<StateResult> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("Country", "onNothingSelected: ");
            }
        });

        mBinding.spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 아이템 클릭
                mState = mStateAdapter.getItem(position);
                mService.getCities(mCountry.getCountry(), mState.getState())
                        .enqueue(new Callback<CityResult>() {
                            @Override
                            public void onResponse(Call<CityResult> call, Response<CityResult> response) {
                                CityResult result = response.body();

                                if (result == null) {
                                    mCityAdapter.clear();
                                    return;
                                }

                                mCityAdapter.clear();
                                mCityAdapter.addAll(result.getData());
                            }

                            @Override
                            public void onFailure(Call<CityResult> call, Throwable t) {

                            }
                        });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("State", "onNothingSelected: ");
            }
        });


    }
}
