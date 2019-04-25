package com.example.recyclerviewexam.gusikdialog

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.recyclerviewexam.R
import kotlinx.android.synthetic.main.activity_gusik_dialog.*
import org.jetbrains.anko.startActivity
import java.util.*

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
            NotificationUtil.showNotification(this, "어쩌구 저쩌구", 1);
        }

        val weatherList = arrayListOf<Weather>()
        weatherList.add(Weather(10, "맑음"))
        weatherList.add(Weather(10, "맑음"))
        weatherList.add(Weather(10, "맑음"))
        weatherList.add(Weather(10, "비"))
        weatherList.add(Weather(10, "맑음"))
        weatherList.add(Weather(10, "맑음"))
        weatherList.add(Weather(20, "비"))
        weatherList.add(Weather(10, "맑음"))
        weatherList.add(Weather(10, "맑음"))
        weatherList.add(Weather(10, "맑음"))


        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        for (weather in weatherList) {
            if (weather.text == "비") {
                val calendar = Calendar.getInstance()   // 현재 시간
                calendar.timeInMillis = System.currentTimeMillis()
                calendar.add(Calendar.SECOND, weather.second)

                val intent = Intent(this, MyWeatherReceiver::class.java)
                intent.putExtra("date", "2019-xx-xx")
                intent.putExtra("text", "비")

                val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

                // 알람 등록
                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent)
            }
        }
    }
}

data class Weather(val second: Int, val text: String)
