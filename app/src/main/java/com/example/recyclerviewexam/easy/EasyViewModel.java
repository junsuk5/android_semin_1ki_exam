package com.example.recyclerviewexam.easy;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class EasyViewModel extends ViewModel {
    public MutableLiveData<Integer> count = new MutableLiveData<>();

    public EasyViewModel() {
        count.setValue(0);
    }
}
