package com.example.recyclerviewexam.network;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.recyclerviewexam.R;

public class BindingAdapterUtil {
    @BindingAdapter("photo")
    public static void showPhoto(ImageView view, String url) {
        Glide.with(view)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }
}
