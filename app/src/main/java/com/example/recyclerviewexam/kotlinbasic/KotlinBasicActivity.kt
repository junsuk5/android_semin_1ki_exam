package com.example.recyclerviewexam.kotlinbasic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.recyclerviewexam.R
import kotlinx.android.synthetic.main.activity_kotlin_basic.*
import org.jetbrains.anko.toast

class KotlinBasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_basic)

        button.setOnClickListener { toast("클릭") }
    }
}
