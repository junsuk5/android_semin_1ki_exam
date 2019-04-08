
package com.example.recyclerviewexam.models;

import java.util.List;

public class CityResult {

    private String status;
    private List<City> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<City> getData() {
        return data;
    }

    public void setData(List<City> data) {
        this.data = data;
    }

}
