package com.example.recyclerviewexam.exam283;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.eventbus.events.ItemClickEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ColorFragment extends Fragment {
    public ColorFragment() {
        // Required empty public constructor
    }

    public static ColorFragment newInstance() {
        return new ColorFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_color, container, false);
    }

    public void setColor(int color) {
        if (getView() != null) {
            getView().setBackgroundColor(color);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ItemClickEvent event) {
        setColor(event.color);
    };

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
