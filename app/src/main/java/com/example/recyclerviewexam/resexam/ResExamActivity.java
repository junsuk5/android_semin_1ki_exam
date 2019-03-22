package com.example.recyclerviewexam.resexam;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.recyclerviewexam.R;

public class ResExamActivity extends AppCompatActivity {
    ConstraintSet mConstraintSet1 = new ConstraintSet();
    ConstraintSet mConstraintSet2 = new ConstraintSet();
    ConstraintLayout mConstraintLayout;
    boolean mOld = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mConstraintSet2.clone(this, R.layout.activity_res_exam2);
        setContentView(R.layout.activity_res_exam);
        mConstraintLayout = findViewById(R.id.activity_main);
        mConstraintSet1.clone(mConstraintLayout);
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

    public void onButtonClicked(View view) {
        TransitionManager.beginDelayedTransition(mConstraintLayout);
        if (mOld = !mOld) {
            mConstraintSet1.applyTo(mConstraintLayout);
        }  else {
            mConstraintSet2.applyTo(mConstraintLayout);
        }
    }
}
