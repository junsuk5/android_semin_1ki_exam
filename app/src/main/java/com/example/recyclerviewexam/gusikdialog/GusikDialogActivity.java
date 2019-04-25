package com.example.recyclerviewexam.gusikdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.recyclerviewexam.R;

public class GusikDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String hello = getIntent().getStringExtra("hello");

        TextView textView = findViewById(R.id.textView4);
        textView.setText(hello);
    }
}
