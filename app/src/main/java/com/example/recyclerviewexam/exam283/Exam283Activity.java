package com.example.recyclerviewexam.exam283;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.recyclerviewexam.R;

public class Exam283Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam283);

        ColorFragment fragment = new ColorFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.linear_layout, fragment)
                .commit();

        findViewById(R.id.button).setOnClickListener(v -> {
            fragment.setColor(Color.YELLOW);
        });
    }

}
