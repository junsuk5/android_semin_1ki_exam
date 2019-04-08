
package com.example.recyclerviewexam.models;

import java.util.List;

public class CountryResult {

    private String status;
    private List<Country> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Country> getData() {
        return data;
    }

    public void setData(List<Country> data) {
        this.data = data;
    }

}
