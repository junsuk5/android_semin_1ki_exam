package com.example.recyclerviewexam;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recyclerviewexam.aac.CounterActivity;
import com.example.recyclerviewexam.aac.VmShareActivity;
import com.example.recyclerviewexam.airvisual.AirVisualActivity;
import com.example.recyclerviewexam.broadcast.BatteryReceiver;
import com.example.recyclerviewexam.countdown.CountDownActivity;
import com.example.recyclerviewexam.databinding.DataBindingActivity;
import com.example.recyclerviewexam.easy.EasyActivity;
import com.example.recyclerviewexam.eventbus.EventBusExamActivity;
import com.example.recyclerviewexam.exam283.Exam283Activity;
import com.example.recyclerviewexam.firebase.FirebaseActivity;
import com.example.recyclerviewexam.googlemap.MapsActivity;
import com.example.recyclerviewexam.gusikdialog.GusikDialogMainActivity;
import com.example.recyclerviewexam.jsrecycleradapter.JsRecyclerExamActivity;
import com.example.recyclerviewexam.kotlinbasic.KotlinBasicActivity;
import com.example.recyclerviewexam.kotlinexam.KotlinExamActivity;
import com.example.recyclerviewexam.kotlinexam.jsonplaceholder.LoginActivity;
import com.example.recyclerviewexam.network.NetworkActivity;
import com.example.recyclerviewexam.realm.RealmActivity;
import com.example.recyclerviewexam.resexam.ResExamActivity;
import com.example.recyclerviewexam.thread.ThreadActivity;
import com.example.recyclerviewexam.todos.TodosActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ExamRecyclerAdapter.MyOnClickListener {

    private ExamRecyclerAdapter mAdapter;

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "default";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("default", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        // Data
        List<Class> dataList = new ArrayList<>();
        dataList.add(IntentActivity.class);
        dataList.add(SharedPreferenceActivity.class);
        dataList.add(RecyclerViewActivity.class);
        dataList.add(Exam283Activity.class);
        dataList.add(EventBusExamActivity.class);
        dataList.add(ThreadActivity.class);
        dataList.add(ResExamActivity.class);
        dataList.add(CountDownActivity.class);
        dataList.add(NetworkActivity.class);
        dataList.add(KotlinExamActivity.class);
        dataList.add(LoginActivity.class);
        dataList.add(JsRecyclerExamActivity.class);
        dataList.add(TodosActivity.class);
        dataList.add(DataBindingActivity.class);
        dataList.add(RealmActivity.class);
        dataList.add(FirebaseActivity.class);
        dataList.add(MapsActivity.class);
        dataList.add(AirVisualActivity.class);
        dataList.add(CounterActivity.class);
        dataList.add(VmShareActivity.class);
        dataList.add(EasyActivity.class);
        dataList.add(KotlinBasicActivity.class);
        dataList.add(GusikDialogMainActivity.class);

        // Adapter
        mAdapter = new ExamRecyclerAdapter();

        // View
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(decoration);


        // 나중에 아이템 갱신
        mAdapter.setItems(dataList);
        mAdapter.notifyDataSetChanged();

        // 클릭 이벤트 설정
        mAdapter.setMyOnClickListener(this);
    }

    private void moveActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        overridePendingTransition(R.anim.shake, R.anim.out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_remove:
//                if (mAdapter.removeItems()) {
//                    Toast.makeText(this, "몇 건이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "삭제할 아이템을 선택하세요", Toast.LENGTH_SHORT).show();
//                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSelected(Class clazz) {
        moveActivity(clazz);
    }


    BroadcastReceiver br = new BatteryReceiver();

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        this.registerReceiver(br, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }
}
