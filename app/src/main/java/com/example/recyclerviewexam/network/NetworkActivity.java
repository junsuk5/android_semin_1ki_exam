package com.example.recyclerviewexam.network;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.recyclerviewexam.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkActivity extends AppCompatActivity {

    private static final String TAG = NetworkActivity.class.getSimpleName();

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        mProgressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        PhotoRecyclerAdapter adapter = new PhotoRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonplaceHolderService service = retrofit.create(JsonplaceHolderService.class);

        service.listPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                mProgressBar.setVisibility(View.GONE);
                // 응답
                List<Photo> photoList = response.body();
                Log.d(TAG, "onResponse: 크기 : " + photoList.size());
                Log.d(TAG, "onResponse: " + photoList);

                adapter.setItems(photoList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                // 실패
                Toast.makeText(NetworkActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    static class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoRecyclerAdapter.PhotoHolder> {
        private List<Photo> mItems = new ArrayList<>();

        public void setItems(List<Photo> items) {
            mItems = items;
        }

        @NonNull
        @Override
        public PhotoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_photo, viewGroup, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PhotoHolder photoHolder, int i) {
            Photo photo = mItems.get(i);

            Glide.with(photoHolder.itemView)
                    .load(photo.getThumbnailUrl())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(photoHolder.thumbnailImageView);

            photoHolder.titleTextView.setText(photo.getTitle());
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        static class PhotoHolder extends RecyclerView.ViewHolder {
            public ImageView thumbnailImageView;
            public TextView titleTextView;

            public PhotoHolder(@NonNull View itemView) {
                super(itemView);

                thumbnailImageView = itemView.findViewById(R.id.thumbnail_image_view);
                titleTextView = itemView.findViewById(R.id.title_text_view);
            }
        }
    }


}
