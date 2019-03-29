package com.example.recyclerviewexam.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.recyclerviewexam.R;

public class DataBindingActivity extends AppCompatActivity {

    User user = new User("준석", "오");
    private ActivityDataBindingBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);


        mBinding.setUser(user);
        mBinding.myName.setText("영석");
    }

    public void onValueChange(View view) {
        user.setFirstName("마이클");
    }
}
