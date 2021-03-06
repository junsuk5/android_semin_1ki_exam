package com.example.recyclerviewexam.exam283;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.recyclerviewexam.R;
import com.example.recyclerviewexam.eventbus.events.ItemClickEvent;
import com.example.recyclerviewexam.eventbus.events.SecondEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ColorFragment extends Fragment {
    private int mColor;

    public ColorFragment() {
        // Required empty public constructor
    }

    public static ColorFragment newInstance(int color) {
        ColorFragment fragment = new ColorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("color", color);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColor = getArguments().getInt("color");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_color, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setColor(mColor);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSecondMessageEvent(SecondEvent event) {
        Toast.makeText(getActivity(), event.toString(), Toast.LENGTH_SHORT).show();
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
