package com.example.recyclerviewexam.aac;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.databinding.ActivityCounterBinding;

public class CounterActivity extends AppCompatActivity {

    private ActivityCounterBinding mBinding;
    private CounterViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_counter);

        mViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);


        // 클릭
        mBinding.fabAdd.setOnClickListener(v -> mViewModel.increase());
        mBinding.fabRemove.setOnClickListener(v -> mViewModel.decrease());


        // UI 업데이트
        mViewModel.counterLiveData.observe(this, integer -> {
            mBinding.countTextView.setText(integer + "");
        });
    }
}
