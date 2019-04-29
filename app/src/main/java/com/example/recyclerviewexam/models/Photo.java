package com.example.recyclerviewexam.models;

import android.net.Uri;

public class Photo {
    private String path;
    private Uri uri;

    public Photo() {
    }

    public Photo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Photo{");
        sb.append("path='").append(path).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
