
package com.example.recyclerviewexam.models;

import java.util.List;

public class StateResult {

    private String status;
    private List<State> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<State> getData() {
        return data;
    }

    public void setData(List<State> data) {
        this.data = data;
    }

}
