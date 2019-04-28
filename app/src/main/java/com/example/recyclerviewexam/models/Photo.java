package com.example.recyclerviewexam.models;

public class Photo {
    private String uri;

    public Photo(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Photo{");
        sb.append("uri='").append(uri).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
