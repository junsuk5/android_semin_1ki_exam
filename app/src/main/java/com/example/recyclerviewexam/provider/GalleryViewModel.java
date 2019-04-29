package com.example.recyclerviewexam.provider;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.example.recyclerviewexam.models.Photo;

import java.util.ArrayList;
import java.util.List;

public class GalleryViewModel extends AndroidViewModel {
    MutableLiveData<List<Photo>> photos = new MutableLiveData<>();

    public GalleryViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchPhotos() {


        // SELECT date, title FROM gallery
        // WHERE date >= 2019-04-01
        // ORDER BY date DESC

        // SELECT * FROM gallery
        Cursor cursor = getApplication().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,   // gallery
                null,   // new String[] { "date", "title" }
                null,   // "date >= 2019-04-01"
                null,   //
                null    // date DESC
        );

        // Cursor to List
        List<Photo> photoList = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                String uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                Photo photo = new Photo();
                photo.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
                photo.setUri(Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, uri));
                photoList.add(photo);
            }
            cursor.close();
        }
        photos.setValue(photoList);

    }
}
