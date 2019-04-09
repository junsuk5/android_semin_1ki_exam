package com.example.recyclerviewexam.aac;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.databinding.ActivityCounterBinding;

public class CounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCounterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_counter);
        binding.setLifecycleOwner(this);

        // ViewModel
        CounterViewModel viewModel = ViewModelProviders.of(this).get(CounterViewModel.class);

        binding.setViewModel(viewModel);
    }
}
