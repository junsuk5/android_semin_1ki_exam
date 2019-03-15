package com.example.recyclerviewexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerviewexam.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactRecyclerAdapter.MyOnContactClickListener {

    private ContactRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data
        List<Contact> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add(new Contact("아무개 " + (i + 1) + "호"));
        }

        // Adapter
        mAdapter = new ContactRecyclerAdapter();

        // View
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(mAdapter);


        // 나중에 아이템 갱신
        mAdapter.setItems(dataList);
        mAdapter.notifyDataSetChanged();

        // 클릭 이벤트 설정
        mAdapter.setMyOnContactClickListener(this);
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
                if (mAdapter.removeItems()) {
                    Toast.makeText(this, "몇 건이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "삭제할 아이템을 선택하세요", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onContactSelected(View view, Contact contact, int position) {
        Toast.makeText(this, position + ": " + contact, Toast.LENGTH_SHORT).show();
    }
}
