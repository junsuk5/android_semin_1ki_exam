package com.example.recyclerviewexam.aac;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    public MutableLiveData<Integer> counterLiveData = new MutableLiveData<>();

    public CounterViewModel() {
        counterLiveData.setValue(0);
    }

    public int getCounter() {
        return counterLiveData.getValue();
    }

    public void setCounter(int counterLiveData) {
        this.counterLiveData.setValue(counterLiveData);
    }

    public void increase() {
        setCounter(getCounter() + 1);
    }

    public void decrease() {
        setCounter(getCounter() - 1);
    }
}
