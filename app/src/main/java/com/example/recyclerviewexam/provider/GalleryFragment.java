package com.example.recyclerviewexam.provider;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.databinding.ItemGalleryBinding;
import com.example.recyclerviewexam.models.Photo;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private static final String TAG = GalleryFragment.class.getSimpleName();
    private GalleryViewModel mViewModel;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gallery_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);

        PhotoAdapter adapter = initUI(getView());

        setObserver(adapter);

        requestPermission();
    }

    @NotNull
    private PhotoAdapter initUI(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        PhotoAdapter adapter = new PhotoAdapter(model -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, model.getUri());
            shareIntent.setType("image/*");
            if (shareIntent.resolveActivity(requireContext().getPackageManager()) != null) {
                startActivity(shareIntent);
            }
        });
        recyclerView.setAdapter(adapter);
        return adapter;
    }

    private void setObserver(PhotoAdapter adapter) {
        mViewModel.photos.observe(this, photos -> {
            if (photos != null) {
                Log.d(TAG, "onViewCreated: " + photos.toString());
                adapter.setItems(photos);
            }
        });
    }

    private void requestPermission() {
        TedPermission.with(requireContext())
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        mViewModel.fetchPhotos();
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        Toast.makeText(requireContext(), "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                }).setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private static class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
        interface OnPhotoClickListener {
            void onPhotoClicked(Photo model);
        }

        private OnPhotoClickListener mListener;

        private List<Photo> mItems = new ArrayList<>();

        public PhotoAdapter() {
        }

        public PhotoAdapter(OnPhotoClickListener listener) {
            mListener = listener;
        }

        public void setItems(List<Photo> items) {
            this.mItems = items;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gallery, parent, false);
            final PhotoViewHolder viewHolder = new PhotoViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        final Photo item = mItems.get(viewHolder.getAdapterPosition());
                        mListener.onPhotoClicked(item);
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
            Photo item = mItems.get(position);
            holder.binding.setPhoto(item);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public static class PhotoViewHolder extends RecyclerView.ViewHolder {
            ItemGalleryBinding binding;
            public PhotoViewHolder(@NonNull View itemView) {
                super(itemView);
                binding = ItemGalleryBinding.bind(itemView);
            }
        }
    }
}
