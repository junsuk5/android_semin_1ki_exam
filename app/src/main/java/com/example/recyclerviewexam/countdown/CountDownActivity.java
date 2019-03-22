package com.example.recyclerviewexam.countdown;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.recyclerviewexam.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CountDownActivity extends AppCompatActivity
        implements CountDownFragment.OnCountDownFragmentListener {

    private TextView mCountTextView;
    private CountDownFragment mCountDownFragment;
    private CountDownTask mCountDownTask;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCountDownEvent(CountDownEvent event) {
        mCountDownFragment.setCount(event.count);
        mCountTextView.setText(event.count + "");
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        mCountTextView = findViewById(R.id.count_text_view);

        mCountDownFragment = new CountDownFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frag_countdown, mCountDownFragment)
                    .commit();
        }
    }

    @Override
    public void onStartButtonClicked() {
        mCountDownTask = new CountDownTask();
        mCountDownTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onInitButtonClicked() {
        mCountDownTask.cancel(true);
        mCountDownTask = null;
        mCountTextView.setText("0");
        mCountDownFragment.setCount(0);
    }

    static class CountDownEvent{
        int count;
    }

    static class CountDownTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            CountDownEvent event = new CountDownEvent();
            event.count = values[0];
            EventBus.getDefault().post(event);
        }
    }
}
