package com.example.recyclerviewexam.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.recyclerviewexam.R;

public class ThreadActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private TextView mPercentTextView;

    private int mPercent = 0;

    private DownloadTask downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        mProgressBar = findViewById(R.id.progressBar);
        mPercentTextView = findViewById(R.id.percent_text);

        findViewById(R.id.button).setOnClickListener(v -> {
//            if (downloadTask == null) {
//                downloadTask = new DownloadTask();
//                downloadTask.execute(0);
//            } else {
//                downloadTask.cancel(true);
//                downloadTask = null;
//            }

            new DownloadTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0);
            new DownloadTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0);
            new DownloadTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0);
        });
    }

    class DownloadTask extends AsyncTask<Integer, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 초기화
        }

        // 백그라운드에서 오래 걸리는 처리 (Thread)
        @Override
        protected Boolean doInBackground(Integer... integers) {
            for (int i = integers[0]; i <= 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // UI 갱신 요청
                publishProgress(i);
            }
            return true;
        }

        // UI 그리는 부분 (Main Thread)
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0]);
            mPercentTextView.setText(values[0] + " %");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            // 종료하면서 뭔가 작성
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();


        }
    }
}
