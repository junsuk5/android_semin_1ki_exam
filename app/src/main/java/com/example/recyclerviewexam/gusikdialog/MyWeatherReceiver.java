package com.example.recyclerviewexam.gusikdialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyWeatherReceiver extends BroadcastReceiver {
    private int notiId = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "알림!!!!!!!!!!", Toast.LENGTH_SHORT).show();

        String date = intent.getStringExtra("date");
        String text = intent.getStringExtra("text");

        // 노티
        NotificationUtil.showNotification(context, date + "에 " + text + "입니다", notiId);
        notiId++;
    }
}
