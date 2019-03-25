package com.example.recyclerviewexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recyclerviewexam.countdown.CountDownActivity;
import com.example.recyclerviewexam.eventbus.EventBusExamActivity;
import com.example.recyclerviewexam.exam283.Exam283Activity;
import com.example.recyclerviewexam.network.NetworkActivity;
import com.example.recyclerviewexam.resexam.ResExamActivity;
import com.example.recyclerviewexam.thread.ThreadActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ExamRecyclerAdapter.MyOnClickListener {

    private ExamRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
