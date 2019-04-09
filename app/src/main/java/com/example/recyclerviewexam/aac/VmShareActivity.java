package com.example.recyclerviewexam.aac;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.databinding.ActivityVmShareBinding;

public class VmShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityVmShareBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_vm_share);

    }
}
