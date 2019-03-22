package com.example.recyclerviewexam.resexam;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.recyclerviewexam.R;

public class ResExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_exam);

    }

    public void onClick(View view) {
//        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.out);
        view.startAnimation(shake);
    }

    public void onImageViewClick(View view) {
        Intent intent = new Intent(this, TransitionTargetActivity.class);

        ActivityOptions options = ActivityOptions
                .makeSceneTransitionAnimation(this, view, "icon");
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}
