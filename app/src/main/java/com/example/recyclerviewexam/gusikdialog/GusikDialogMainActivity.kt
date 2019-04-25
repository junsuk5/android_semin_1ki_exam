package com.example.recyclerviewexam.gusikdialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.recyclerviewexam.R
import kotlinx.android.synthetic.main.activity_gusik_dialog.*
import org.jetbrains.anko.startActivity

class GusikDialogMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gusik_dialog)

        button.setOnClickListener {
            startActivity<GusikDialogActivity>(
                    "hello" to "헬로",
                    "world" to "월드",
                    "int" to 100)
        }

        notification_button.setOnClickListener {
            NotificationUtil.showNotification(this);
        }
    }
}
