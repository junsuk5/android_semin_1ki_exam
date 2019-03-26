package com.example.recyclerviewexam.kotlinexam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.recyclerviewexam.R
import com.example.recyclerviewexam.network.NetworkActivity
import kotlinx.android.synthetic.main.activity_kotlin_exam.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class KotlinExamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_exam)

        my_button.setOnClickListener {
//            Toast.makeText(this, "클릭!!!!", Toast.LENGTH_SHORT).show()

            // startActivity(new Intent(this, NetworkActivity.class);
            // intent.putExtra("id", 1);

//            startActivity(Intent(this, NetworkActivity::class.java))

            // Anko
            startActivity<NetworkActivity>()

            toast("클릭!!!")
        }
    }
}
