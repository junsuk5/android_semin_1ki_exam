package com.example.recyclerviewexam.easy;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.databinding.ActivityEasyBinding;

public class EasyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEasyBinding binding = DataBindingUtil.setContentView(this,
                        R.layout.activity_easy);

        EasyViewModel viewModel = ViewModelProviders.of(this).get(EasyViewModel.class);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        binding.addButton.setOnClickListener(v -> {
            viewModel.count.setValue(viewModel.count.getValue() + 1);
        });
        binding.subButton.setOnClickListener(v -> {
            viewModel.count.setValue(viewModel.count.getValue() - 1);
        });
    }

    @BindingAdapter("myBind")
    public static void myBind(ImageView imageView, int count) {
        if (count >= 5) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
