package com.example.recyclerviewexam.network;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.databinding.ActivityNetworkBinding;
import com.example.recyclerviewexam.databinding.ItemPhotoBinding;

import java.util.ArrayList;
import java.util.List;

public class NetworkActivity extends AppCompatActivity {

    private static final String TAG = NetworkActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNetworkBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_network);

        NetworkViewModel viewModel = ViewModelProviders.of(this).get(NetworkViewModel.class);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        PhotoRecyclerAdapter adapter = new PhotoRecyclerAdapter();
        binding.recyclerView.setAdapter(adapter);


        viewModel.photoList.observe(this, photos -> {
            adapter.setItems(photos);
            adapter.notifyDataSetChanged();
        });

        viewModel.fetch();

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
            photoHolder.binding.setPhoto(photo);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        static class PhotoHolder extends RecyclerView.ViewHolder {
            public ItemPhotoBinding binding;

            public PhotoHolder(@NonNull View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }
        }
    }


}
